package com.shirdi.darshan.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shirdi.darshan.dtos.AartiBookingRequestDTO;
import com.shirdi.darshan.dtos.AartiBookingResponseDTO;
import com.shirdi.darshan.entities.AartiBooking;
import com.shirdi.darshan.entities.Payment;
import com.shirdi.darshan.enums.BookingTypeEnum;
import com.shirdi.darshan.enums.PaymentMethodEnum;
import com.shirdi.darshan.enums.PaymentStatusEnum;
import com.shirdi.darshan.repository.AartiBookingRepository;
import com.shirdi.darshan.repository.AartiTypeRepository;
import com.shirdi.darshan.repository.PaymentRepository;
import com.shirdi.darshan.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AartiServiceImpl implements AartiService {

    private final AartiBookingRepository repository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final AartiTypeRepository aartiTypeRepository;

    @Override
    public List<String> getAartiTypes() {
        return aartiTypeRepository.findByIsActiveTrue().stream()
                .map(aarti -> aarti.getName() + "|" + 
                              aarti.getTimings() + "|" + 
                              aarti.getPrice() + "|" + 
                              (aarti.getDescription() != null ? aarti.getDescription() : "") + "|" +
                              (aarti.getDurationMinutes() != null ? aarti.getDurationMinutes() : 30) + "|" +
                              (aarti.getGuidelines() != null ? aarti.getGuidelines() : "Please arrive 15 minutes early") + "|" +
                              (aarti.getMaxCapacity() != null ? aarti.getMaxCapacity() : 100))
                .toList();
    }

    @Override
    public String getSchedule(String date) {
        return "Aarti schedule for " + date;
    }

    @Override
    public String getPrices() {
        return "Morning Aarti: ₹200, Evening Aarti: ₹300";
    }

    @Override
    public Map<String, Integer> getAvailableCapacity(String aartiType, String date) {
        var aartiTypeEntity = aartiTypeRepository.findByIsActiveTrue().stream()
                .filter(a -> a.getName().equals(aartiType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aarti type not found"));
        
        int maxCapacity = aartiTypeEntity.getMaxCapacity() != null ? aartiTypeEntity.getMaxCapacity() : 100;
        
        long bookedCount = repository.findAll().stream()
                .filter(b -> b.getAartiType().equals(aartiType)
                        && b.getBookingDate().toString().equals(date)
                        && !b.getStatus().equals("CANCELLED"))
                .mapToInt(b -> b.getNumberOfPeople() != null ? b.getNumberOfPeople() : 1)
                .sum();
        
        int available = maxCapacity - (int) bookedCount;
        
        Map<String, Integer> capacity = new HashMap<>();
        capacity.put("maxCapacity", maxCapacity);
        capacity.put("bookedCount", (int) bookedCount);
        capacity.put("availableCapacity", available);
        
        return capacity;
    }

    @Override
    public AartiBookingResponseDTO bookAarti(AartiBookingRequestDTO dto) {
        // Look up aarti type from database
        var aartiTypeEntity = aartiTypeRepository.findByIsActiveTrue().stream()
                .filter(a -> a.getName().equals(dto.getAartiType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aarti type not found"));
        
        // Check for duplicate booking
        boolean alreadyBooked = repository.findByUserId(dto.getUserId()).stream()
                .anyMatch(b -> b.getAartiType().equals(dto.getAartiType()) 
                        && b.getBookingDate().equals(dto.getBookingDate())
                        && !b.getStatus().equals("CANCELLED"));
        
        if (alreadyBooked) {
            throw new RuntimeException("You have already booked this aarti for the selected date");
        }
        
        // Check capacity
        int numberOfPeople = dto.getNumberOfPeople() != null ? dto.getNumberOfPeople() : 1;
        if (aartiTypeEntity.getMaxCapacity() != null) {
            long bookedCount = repository.findAll().stream()
                    .filter(b -> b.getAartiType().equals(dto.getAartiType())
                            && b.getBookingDate().equals(dto.getBookingDate())
                            && !b.getStatus().equals("CANCELLED"))
                    .mapToInt(b -> b.getNumberOfPeople() != null ? b.getNumberOfPeople() : 1)
                    .sum();
            
            if (bookedCount + numberOfPeople > aartiTypeEntity.getMaxCapacity()) {
                throw new RuntimeException("Not enough capacity available. Only " + 
                        (aartiTypeEntity.getMaxCapacity() - bookedCount) + " spots remaining");
            }
        }
        
        String aartiTime = aartiTypeEntity.getTimings();
        double aartiPrice = aartiTypeEntity.getPrice() * numberOfPeople;
        
        AartiBooking booking = new AartiBooking();
        booking.setAartiType(dto.getAartiType());
        booking.setAartiTime(aartiTime);
        booking.setBookingDate(dto.getBookingDate());
        booking.setUserId(dto.getUserId());
        booking.setNumberOfPeople(numberOfPeople);
        booking.setPrice(aartiPrice);
        booking.setStatus("BOOKED");

        AartiBooking saved = repository.save(booking);
        
        // Create payment record
        Payment payment = new Payment();
        payment.setUser(userRepository.findById(Long.valueOf(dto.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found")));
        payment.setBookingType(BookingTypeEnum.AARTI);
        payment.setBookingId(Long.valueOf(saved.getId()));
        payment.setAmount(aartiPrice);
        payment.setStatus(PaymentStatusEnum.SUCCESS);
        payment.setPaymentMethod(PaymentMethodEnum.ONLINE);
        payment.setTransactionId("AARTI-TXN-" + System.currentTimeMillis());
        
        paymentRepository.save(payment);

        return new AartiBookingResponseDTO(
                saved.getId(),
                "Aarti booked successfully"
        );
    }

    @Override
    public String cancelBooking(int bookingId) {
        AartiBooking booking = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("CANCELLED");
        repository.save(booking);

        return "Aarti booking cancelled successfully";
    }
    
    @Override
    public AartiBooking getBookingById(int bookingId) {
        return repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Aarti booking not found"));
    }

    @Override
    public List<AartiBooking> getBookingsByUserId(int userId) {
        return repository.findByUserId(userId);
    }

}

package com.shirdi.darshan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shirdi.darshan.dtos.DevoteeDetailsDto;
import com.shirdi.darshan.dtos.PoojaBookingRequestDto;
import com.shirdi.darshan.dtos.PoojaBookingResponseDto;
import com.shirdi.darshan.dtos.PoojaTypeResponseDto;
import com.shirdi.darshan.entities.Payment;
import com.shirdi.darshan.entities.PoojaBooking;
import com.shirdi.darshan.entities.PoojaType;
import com.shirdi.darshan.entities.User;
import com.shirdi.darshan.enums.BookingStatusEnum;
import com.shirdi.darshan.enums.BookingTypeEnum;
import com.shirdi.darshan.enums.GenderEnum;
import com.shirdi.darshan.enums.PaymentMethodEnum;
import com.shirdi.darshan.enums.PaymentStatusEnum;
import com.shirdi.darshan.repository.PaymentRepository;
import com.shirdi.darshan.repository.PoojaBookingRepository;
import com.shirdi.darshan.repository.PoojaTypeRepository;
import com.shirdi.darshan.repository.PoojaTimeSlotRepository;
import com.shirdi.darshan.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PoojaServiceImpl implements PoojaService {

    private final PoojaTypeRepository poojaTypeRepository;
    private final PoojaBookingRepository poojaBookingRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final PoojaTimeSlotRepository poojaTimeSlotRepository;

    /* =========================
       GET ACTIVE POOJA TYPES
       ========================= */

    @Override
    public List<PoojaTypeResponseDto> getActivePoojaTypes() {
        return poojaTypeRepository.findByActiveTrue()
                .stream()
                .map(this::mapToPoojaTypeResponseDto)
                .collect(Collectors.toList());
    }

    /* =========================
       GET ACTIVE TIME SLOTS
       ========================= */

    @Override
    public List<String> getActivePoojaTimeSlots() {
        return poojaTimeSlotRepository.findByIsActiveTrueOrderByIdAsc()
                .stream()
                .map(slot -> slot.getSlotTime())
                .collect(Collectors.toList());
    }
    
    @Override
    public Map<String, Integer> getAvailableSlotCapacity(String date, String timeSlot) {
        // Fixed capacity per slot (can be made configurable later)
        int maxCapacity = 5;
        
        long bookedCount = poojaBookingRepository.findAll().stream()
                .filter(b -> b.getPoojaDate().toString().equals(date)
                        && b.getTimeSlot().equals(timeSlot)
                        && b.getStatus() == BookingStatusEnum.CONFIRMED)
                .count();
        
        int available = maxCapacity - (int) bookedCount;
        
        Map<String, Integer> capacity = new HashMap<>();
        capacity.put("maxCapacity", maxCapacity);
        capacity.put("bookedCount", (int) bookedCount);
        capacity.put("availableCapacity", available);
        
        return capacity;
    }

    /* =========================
       BOOK POOJA
       ========================= */

    @Override
    public PoojaBookingResponseDto bookPooja(Long userId, PoojaBookingRequestDto requestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PoojaType poojaType = poojaTypeRepository
                .findByPoojaType(requestDto.getPoojaType())
                .orElseThrow(() -> new RuntimeException("Invalid pooja type"));

        PoojaBooking booking = new PoojaBooking();
        booking.setUser(user);
        booking.setPoojaType(poojaType);
        booking.setPoojaDate(requestDto.getDate());
        booking.setTimeSlot(requestDto.getTimeSlot());
        booking.setTotalAmount(requestDto.getTotalAmount());
        booking.setStatus(BookingStatusEnum.CONFIRMED);

        // Devotee details
        DevoteeDetailsDto devotee = requestDto.getDevoteeDetails();
        booking.setDevoteeName(devotee.getName());
        booking.setDevoteeAge(devotee.getAge());
        booking.setDevoteeGender(GenderEnum.valueOf(devotee.getGender()));
        booking.setDevoteeMobile(devotee.getMobile());
        booking.setDevoteeEmail(devotee.getEmail());
        booking.setSankalp(devotee.getSankalp());
        booking.setGotra(devotee.getGotra());
        booking.setAddress(devotee.getAddress());

        PoojaBooking savedBooking = poojaBookingRepository.save(booking);
        
        // Create payment record
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setBookingType(BookingTypeEnum.POOJA);
        payment.setBookingId(savedBooking.getId());
        payment.setAmount(requestDto.getTotalAmount());
        payment.setStatus(PaymentStatusEnum.SUCCESS); // Auto-approve
        payment.setPaymentMethod(PaymentMethodEnum.ONLINE);
        payment.setTransactionId("POOJA-TXN-" + System.currentTimeMillis());
        
        paymentRepository.save(payment);

        return mapToPoojaBookingResponseDto(savedBooking);
    }

    /* =========================
       GET USER BOOKINGS
       ========================= */

    @Override
    public List<PoojaBookingResponseDto> getUserPoojaBookings(Long userId) {
        return poojaBookingRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToPoojaBookingResponseDto)
                .collect(Collectors.toList());
    }

    /* =========================
       MAPPERS
       ========================= */

    private PoojaTypeResponseDto mapToPoojaTypeResponseDto(PoojaType entity) {
        PoojaTypeResponseDto dto = new PoojaTypeResponseDto();
        dto.setId(entity.getId());
        dto.setPoojaType(entity.getPoojaType());
        dto.setDisplayName(entity.getDisplayName());
        dto.setDescription(entity.getDescription());
        dto.setDurationMinutes(entity.getDurationMinutes());
        dto.setPrice(entity.getPrice());
        dto.setBenefits(entity.getBenefits());
        dto.setRequiredMaterials(entity.getRequiredMaterials());
        return dto;
    }

    private PoojaBookingResponseDto mapToPoojaBookingResponseDto(PoojaBooking entity) {
        PoojaBookingResponseDto dto = new PoojaBookingResponseDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setCategory(entity.getPoojaType().getPoojaType().name());
        dto.setDate(entity.getPoojaDate());
        dto.setTimeSlot(entity.getTimeSlot());
        dto.setTotalAmount(entity.getTotalAmount());

        // Map devotee details
        DevoteeDetailsDto devoteeDto = new DevoteeDetailsDto();
        devoteeDto.setName(entity.getDevoteeName());
        devoteeDto.setAge(entity.getDevoteeAge());
        devoteeDto.setGender(entity.getDevoteeGender().name());
        devoteeDto.setMobile(entity.getDevoteeMobile());
        devoteeDto.setEmail(entity.getDevoteeEmail());
        devoteeDto.setSankalp(entity.getSankalp());
        devoteeDto.setGotra(entity.getGotra());
        devoteeDto.setAddress(entity.getAddress());

        dto.setDevoteeDetails(devoteeDto);

        // Map status
        if (entity.getStatus() == BookingStatusEnum.CONFIRMED) {
            dto.setStatus("upcoming");
        } else {
            dto.setStatus(entity.getStatus().name().toLowerCase());
        }

        return dto;
    }
}

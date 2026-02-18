package com.shirdi.darshan.service;

import com.shirdi.darshan.dtos.*;
import com.shirdi.darshan.entities.DarshanBooking;
import com.shirdi.darshan.entities.DarshanSlot;
import com.shirdi.darshan.entities.Payment;
import com.shirdi.darshan.entities.User;
import com.shirdi.darshan.enums.BookingStatusEnum;
import com.shirdi.darshan.enums.BookingTypeEnum;
import com.shirdi.darshan.enums.DarshanTypeEnum;
import com.shirdi.darshan.enums.PaymentMethodEnum;
import com.shirdi.darshan.enums.PaymentStatusEnum;
import com.shirdi.darshan.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DarshanServiceImpl implements DarshanService {
	private final DarshanSlotRepository darshanSlotRepository;
	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	private final PaymentRepository paymentRepository;
	private final DarshanTypeRepository darshanTypeRepository;
	
	@Override
	public List<DarshanTypeDTO> getDarshanTypes() {
		return darshanTypeRepository.findByIsActiveTrue().stream()
				.map(type -> {
					DarshanTypeDTO dto = new DarshanTypeDTO();
					dto.setId(type.getId());
					dto.setName(type.getName());
					dto.setDescription(type.getDescription());
					dto.setBasePrice(type.getBasePrice());
					dto.setIsActive(type.getIsActive());
					return dto;
				})
				.collect(Collectors.toList());
	}
	
	@Override
	public List<DarshanSlotDTO> getAvailableSlots(LocalDate date) {
		List<DarshanSlot> slots = darshanSlotRepository.findByIsActiveTrue();
		return slots.stream()
				.map(this::mapToSlotDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<DarshanPriceDTO> getPrices() {
		List<DarshanSlot> slots = darshanSlotRepository.findByIsActiveTrue();
		return slots.stream()
				.collect(Collectors.groupingBy(DarshanSlot::getType))
				.entrySet().stream()
				.map(entry -> {
					DarshanPriceDTO dto = new DarshanPriceDTO();
					dto.setType(entry.getKey());
					dto.setPrice(entry.getValue().get(0).getPrice());
					return dto;
				})
				.collect(Collectors.toList());
	}
	
	@Override
	public BookingResponseDTO bookDarshan(BookingRequestDTO request) {
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		DarshanSlot slot = darshanSlotRepository.findById(request.getDarshanSlotId())
				.orElseThrow(() -> new RuntimeException("Darshan slot not found"));
		
		if (slot.getAvailableSlots() < request.getNumberOfPeople()) {
			throw new RuntimeException("Not enough slots available");
		}
		
		DarshanBooking booking = new DarshanBooking();
		booking.setUser(user);
		booking.setDarshanSlot(slot);
		booking.setBookingDate(request.getBookingDate());
		booking.setNumberOfPeople(request.getNumberOfPeople());
		booking.setStatus(BookingStatusEnum.CONFIRMED);
		
		slot.setAvailableSlots(slot.getAvailableSlots() - request.getNumberOfPeople());
		darshanSlotRepository.save(slot);
		
		DarshanBooking savedBooking = bookingRepository.save(booking);
		
		// Create payment record
		Double totalAmount = slot.getPrice() * request.getNumberOfPeople();
		Payment payment = new Payment();
		payment.setUser(user);
		payment.setBookingType(BookingTypeEnum.DARSHAN);
		payment.setBookingId(savedBooking.getId());
		payment.setAmount(totalAmount);
		
		// Free darshan (amount = 0) auto-approved, paid darshan pending
		if (totalAmount == 0) {
			payment.setStatus(PaymentStatusEnum.SUCCESS);
			payment.setPaymentMethod(PaymentMethodEnum.FREE);
			payment.setTransactionId("FREE-" + System.currentTimeMillis());
		} else {
			payment.setStatus(PaymentStatusEnum.SUCCESS); // Auto-approve for now
			payment.setPaymentMethod(PaymentMethodEnum.ONLINE);
			payment.setTransactionId("TXN-" + System.currentTimeMillis());
		}
		
		paymentRepository.save(payment);
		
		return mapToBookingResponseDTO(savedBooking);
	}
	
	@Override
	public List<BookingResponseDTO> getUserBookings(Long userId) {
		List<DarshanBooking> bookings = bookingRepository.findByUserId(userId);
		return bookings.stream()
				.map(this::mapToBookingResponseDTO)
				.collect(Collectors.toList());
	}
	
	@Override
	public BookingResponseDTO getBookingDetails(Long bookingId) {
		DarshanBooking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		return mapToBookingResponseDTO(booking);
	}
	
	@Override
	public ApiResponse cancelBooking(Long bookingId) {
		DarshanBooking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found"));
		
		booking.setStatus(BookingStatusEnum.CANCELLED);
		
		DarshanSlot slot = booking.getDarshanSlot();
		slot.setAvailableSlots(slot.getAvailableSlots() + booking.getNumberOfPeople());
		darshanSlotRepository.save(slot);
		
		bookingRepository.save(booking);
		return new ApiResponse("Success", "Booking cancelled successfully");
	}
	
	private DarshanSlotDTO mapToSlotDTO(DarshanSlot slot) {
		DarshanSlotDTO dto = new DarshanSlotDTO();
		dto.setId(slot.getId());
		dto.setName(slot.getName());
		dto.setStartTime(slot.getStartTime());
		dto.setEndTime(slot.getEndTime());
		dto.setAvailableSlots(slot.getAvailableSlots());
		dto.setPrice(slot.getPrice());
		dto.setType(slot.getType());
		return dto;
	}
	
	private BookingResponseDTO mapToBookingResponseDTO(DarshanBooking booking) {
		BookingResponseDTO dto = new BookingResponseDTO();
		dto.setId(booking.getId());
		dto.setUserId(booking.getUser().getId());
		dto.setUserName(booking.getUser().getFirstName() + " " + booking.getUser().getLastName());
		dto.setDarshanSlotId(booking.getDarshanSlot().getId());
		dto.setDarshanSlotName(booking.getDarshanSlot().getName());
		dto.setSlotTime(booking.getDarshanSlot().getStartTime() + " - " + booking.getDarshanSlot().getEndTime());
		dto.setBookingDate(booking.getBookingDate());
		dto.setNumberOfPeople(booking.getNumberOfPeople());
		dto.setTotalAmount(booking.getDarshanSlot().getPrice() * booking.getNumberOfPeople());
		dto.setStatus(booking.getStatus());
		dto.setCreatedAt(booking.getCreatedAt());
		return dto;
	}
}

package com.shirdi.darshan.service;

import com.shirdi.darshan.dtos.*;
import java.time.LocalDate;
import java.util.List;

public interface DarshanService {
	List<DarshanTypeDTO> getDarshanTypes();
	List<DarshanSlotDTO> getAvailableSlots(LocalDate date);
	List<DarshanPriceDTO> getPrices();
	BookingResponseDTO bookDarshan(BookingRequestDTO request);
	List<BookingResponseDTO> getUserBookings(Long userId);
	BookingResponseDTO getBookingDetails(Long bookingId);
	ApiResponse cancelBooking(Long bookingId);
}

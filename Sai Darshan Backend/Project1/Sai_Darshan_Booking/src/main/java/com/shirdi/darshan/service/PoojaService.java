package com.shirdi.darshan.service;

import java.util.List;
import java.util.Map;

import com.shirdi.darshan.dtos.PoojaBookingRequestDto;
import com.shirdi.darshan.dtos.PoojaBookingResponseDto;
import com.shirdi.darshan.dtos.PoojaTypeResponseDto;

public interface PoojaService {

    // Get all active pooja types
    List<PoojaTypeResponseDto> getActivePoojaTypes();

    // Get all active pooja time slots
    List<String> getActivePoojaTimeSlots();
    
    // Get available slots for specific date
    Map<String, Integer> getAvailableSlotCapacity(String date, String timeSlot);

    // Create pooja booking
    PoojaBookingResponseDto bookPooja(Long userId, PoojaBookingRequestDto requestDto);

    // Get bookings for logged-in user
    List<PoojaBookingResponseDto> getUserPoojaBookings(Long userId);
}

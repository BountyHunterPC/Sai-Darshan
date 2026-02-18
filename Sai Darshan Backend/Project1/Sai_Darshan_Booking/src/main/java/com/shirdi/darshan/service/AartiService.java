package com.shirdi.darshan.service;

import java.util.List;
import java.util.Map;

import com.shirdi.darshan.dtos.AartiBookingRequestDTO;
import com.shirdi.darshan.dtos.AartiBookingResponseDTO;
import com.shirdi.darshan.entities.AartiBooking;

public interface AartiService {

    // Aarti Master Data
    List<String> getAartiTypes();

    String getSchedule(String date);

    String getPrices();

    // Get available capacity for aarti type on specific date
    Map<String, Integer> getAvailableCapacity(String aartiType, String date);

    // Booking Operations
    AartiBookingResponseDTO bookAarti(AartiBookingRequestDTO dto);

    String cancelBooking(int bookingId);

    // Darshan-style GET APIs for Aarti
    AartiBooking getBookingById(int bookingId);

    List<AartiBooking> getBookingsByUserId(int userId);
}

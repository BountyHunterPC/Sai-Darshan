package com.shirdi.darshan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shirdi.darshan.entities.AartiBooking;
import com.shirdi.darshan.service.AartiService;

@RestController
@RequestMapping("/user/aarti")
public class AartiController {

    @Autowired
    private AartiService aartiService;

    // 1️⃣ Get Aarti Types
    @GetMapping("/types")
    public List<String> getAartiTypes() {
        return aartiService.getAartiTypes();
    }
    
 // 2️⃣ Get Aarti Schedule
    @GetMapping("/schedule")
    public String getSchedule(@RequestParam String date) {
        return aartiService.getSchedule(date);
    }
    
    // 3️⃣ Get Aarti Prices
    @GetMapping("/prices")
    public String getPrices() {
        return aartiService.getPrices();
    }
    
    // 3.5️⃣ Get Available Capacity
    @GetMapping("/capacity")
    public Map<String, Integer> getAvailableCapacity(@RequestParam String aartiType, @RequestParam String date) {
        return aartiService.getAvailableCapacity(aartiType, date);
    }
     
    // 4️⃣ Book Aarti
    @PostMapping("/book")
    public com.shirdi.darshan.dtos.AartiBookingResponseDTO bookAarti(@RequestBody com.shirdi.darshan.dtos.AartiBookingRequestDTO dto) {
        return aartiService.bookAarti(dto);
    }
    
    // 5️⃣ Cancel Aarti Booking
    @PostMapping("/bookings/{bookingId}/cancel")
    public String cancelAartiBooking(@PathVariable int bookingId) {
        return aartiService.cancelBooking(bookingId);
    }
    
   
    
      // 6 Get Aarti Booking by Booking ID
    @GetMapping("/bookings/{bookingId}")
    public AartiBooking getAartiBookingById(@PathVariable int bookingId) {
        return aartiService.getBookingById(bookingId);
    }
   
    // 7️⃣ Get All Aarti Bookings of a User
    @GetMapping("/bookings/user/{userId}")
    public List<AartiBooking> getAartiBookingsByUser(@PathVariable int userId) {
        return aartiService.getBookingsByUserId(userId);
    }
   
     
    
}

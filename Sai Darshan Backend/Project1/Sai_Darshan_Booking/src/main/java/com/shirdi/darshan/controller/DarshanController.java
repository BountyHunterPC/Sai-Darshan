package com.shirdi.darshan.controller;

import com.shirdi.darshan.dtos.*;
import com.shirdi.darshan.service.DarshanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/darshan")
@RequiredArgsConstructor
public class DarshanController {
    private final DarshanService darshanService;
    
    @GetMapping("/types")
    public ResponseEntity<?> getDarshanTypes() {
        try {
            return ResponseEntity.ok(darshanService.getDarshanTypes());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/slots")
    public ResponseEntity<?> getAvailableSlots(@RequestParam LocalDate date) {
        try {
            return ResponseEntity.ok(darshanService.getAvailableSlots(date));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/prices")
    public ResponseEntity<?> getPrices() {
        try {
            return ResponseEntity.ok(darshanService.getPrices());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    
    @PostMapping("/book")
    public ResponseEntity<?> bookDarshan(@RequestBody BookingRequestDTO request) {
        System.out.println("in book darshan " + request);
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(darshanService.bookDarshan(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<?> getUserBookings(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(darshanService.getUserBookings(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<?> getBookingDetails(@PathVariable Long bookingId) {
        try {
            return ResponseEntity.ok(darshanService.getBookingDetails(bookingId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    
    @PostMapping("/bookings/{bookingId}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        try {
            return ResponseEntity.ok(darshanService.cancelBooking(bookingId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}

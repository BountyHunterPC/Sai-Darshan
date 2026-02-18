package com.shirdi.darshan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shirdi.darshan.dtos.PoojaBookingRequestDto;
import com.shirdi.darshan.dtos.PoojaBookingResponseDto;
import com.shirdi.darshan.dtos.PoojaTypeResponseDto;
import com.shirdi.darshan.service.PoojaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pooja")
@RequiredArgsConstructor
public class PoojaController {

    private final PoojaService poojaService;

    /* =========================
       GET POOJA TYPES
       ========================= */

    @GetMapping("/types")
    public ResponseEntity<List<PoojaTypeResponseDto>> getPoojaTypes() {
        return ResponseEntity.ok(poojaService.getActivePoojaTypes());
    }

    /* =========================
       GET TIME SLOTS
       ========================= */

    @GetMapping("/time-slots")
    public ResponseEntity<List<String>> getTimeSlots() {
        return ResponseEntity.ok(poojaService.getActivePoojaTimeSlots());
    }
    
    /* =========================
       GET SLOT CAPACITY
       ========================= */

    @GetMapping("/slot-capacity")
    public ResponseEntity<Map<String, Integer>> getSlotCapacity(
            @RequestParam String date,
            @RequestParam String timeSlot) {
        return ResponseEntity.ok(poojaService.getAvailableSlotCapacity(date, timeSlot));
    }

    /* =========================
       BOOK POOJA
       ========================= */

    @PostMapping("/bookings")
    public ResponseEntity<PoojaBookingResponseDto> bookPooja(
            @RequestParam Long userId,
            @RequestBody @jakarta.validation.Valid PoojaBookingRequestDto requestDto) {

        PoojaBookingResponseDto response =
                poojaService.bookPooja(userId, requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /* =========================
       GET USER BOOKINGS
       ========================= */

    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<List<PoojaBookingResponseDto>> getUserBookings(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                poojaService.getUserPoojaBookings(userId)
        );
    }
}

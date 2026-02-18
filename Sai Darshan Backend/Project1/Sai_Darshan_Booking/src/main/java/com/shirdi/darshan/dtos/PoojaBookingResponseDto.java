package com.shirdi.darshan.dtos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoojaBookingResponseDto {

    private Long id;

    private Long userId;

    // Same as pooja type enum value
    private String category;

    private LocalDate date;

    private String timeSlot;

    private DevoteeDetailsDto devoteeDetails;

    private Double totalAmount;

    // UPCOMING / COMPLETED / CANCELLED (mapped from enum)
    private String status;
}

package com.shirdi.darshan.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO {
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Darshan slot ID is required")
    private Long darshanSlotId;
    
    @NotNull(message = "Booking date is required")
    @FutureOrPresent(message = "Booking date must be today or future")
    private LocalDate bookingDate;
    
    @NotNull(message = "Number of people is required")
    @Min(value = 1, message = "At least 1 person required")
    @Max(value = 10, message = "Maximum 10 people allowed")
    private Integer numberOfPeople;
}

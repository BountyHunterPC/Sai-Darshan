package com.shirdi.darshan.dtos;

import java.time.LocalDate;

import com.shirdi.darshan.enums.PoojaTypeEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoojaBookingRequestDto {

    @NotNull(message = "Pooja type is required")
    private PoojaTypeEnum poojaType;

    @NotNull(message = "Pooja date is required")
    private LocalDate date;

    @NotNull(message = "Time slot is required")
    private String timeSlot;

    @NotNull(message = "Devotee details are required")
    @Valid
    private DevoteeDetailsDto devoteeDetails;

    @NotNull(message = "Total amount is required")
    private Double totalAmount;
}

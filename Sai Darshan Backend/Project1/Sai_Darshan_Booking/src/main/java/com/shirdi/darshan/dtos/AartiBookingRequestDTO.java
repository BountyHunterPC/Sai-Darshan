package com.shirdi.darshan.dtos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AartiBookingRequestDTO {

    private int userId;
    private String aartiType;
    private LocalDate bookingDate;
    private Integer numberOfPeople;
}

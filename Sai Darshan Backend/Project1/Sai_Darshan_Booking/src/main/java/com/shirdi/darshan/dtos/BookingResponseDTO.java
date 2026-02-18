package com.shirdi.darshan.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.shirdi.darshan.entities.*;
import com.shirdi.darshan.enums.BookingStatusEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private String userName;
    private Long darshanSlotId;
    private String darshanSlotName;
    private String slotTime;
    private LocalDate bookingDate;
    private Integer numberOfPeople;
    private Double totalAmount;
    private BookingStatusEnum status;
    private LocalDateTime createdAt;
    private String aartiType;
}
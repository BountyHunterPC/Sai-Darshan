package com.shirdi.darshan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDarshanSlotDTO {
    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private String type;
    private Integer maxCapacity;
    private Integer availableSlots;
    private Double price;
    private Boolean isActive;
}

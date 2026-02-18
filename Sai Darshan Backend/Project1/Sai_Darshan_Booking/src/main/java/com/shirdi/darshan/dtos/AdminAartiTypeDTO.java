package com.shirdi.darshan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminAartiTypeDTO {
    private Long id;
    private String name;
    private String description;
    private String timings;
    private Double price;
    private Integer durationMinutes;
    private String guidelines;
    private Integer maxCapacity;
    private Boolean isActive;
}

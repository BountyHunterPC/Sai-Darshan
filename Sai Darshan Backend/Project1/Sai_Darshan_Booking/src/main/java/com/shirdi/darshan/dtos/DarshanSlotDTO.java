package com.shirdi.darshan.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

import com.shirdi.darshan.entities.*;
import com.shirdi.darshan.enums.DarshanTypeEnum;

@Getter
@Setter
@NoArgsConstructor

public class DarshanSlotDTO {
 private Long id;
 private String name;
 private LocalTime startTime;
 private LocalTime endTime;
 private Integer availableSlots;
 private Double price;
 private DarshanTypeEnum type;
}

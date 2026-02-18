package com.shirdi.darshan.dtos;

import java.time.LocalDate;

import com.shirdi.darshan.entities.*;
import com.shirdi.darshan.enums.DarshanTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DarshanPriceDTO {
    private DarshanTypeEnum type;
    private Double price;
}
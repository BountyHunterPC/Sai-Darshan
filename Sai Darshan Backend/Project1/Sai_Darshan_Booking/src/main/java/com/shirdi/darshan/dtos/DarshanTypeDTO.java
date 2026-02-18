package com.shirdi.darshan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DarshanTypeDTO {
    private Long id;
    private String name;
    private String description;
    private Double basePrice;
    private Boolean isActive;
}

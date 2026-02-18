package com.shirdi.darshan.dtos;

import com.shirdi.darshan.enums.PoojaTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoojaTypeResponseDto {

    private Long id;

    // Stable backend identifier
    private PoojaTypeEnum poojaType;

    // UI display fields
    private String displayName;
    private String description;
    private Integer durationMinutes;
    private Double price;
    private String benefits;
    private String requiredMaterials;
}

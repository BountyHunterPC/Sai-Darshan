package com.shirdi.darshan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminPoojaTypeDTO {
    private Long id;
    private String poojaCode;
    private String displayName;
    private String description;
    private Integer durationMinutes;
    private Double price;
    private String benefits;
    private String requiredMaterials;
    private Boolean active;
}

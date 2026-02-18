package com.shirdi.darshan.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminPoojaTimeSlotDTO {
    private Long id;
    private String slotTime;
    private Boolean isActive;
}

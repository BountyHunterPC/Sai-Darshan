package com.shirdi.darshan.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminStatsDTO {
    private Long darshanCount;
    private Long aartiCount;
    private Long poojaCount;
    private Double totalDonations;
    private Long totalUsers;
}

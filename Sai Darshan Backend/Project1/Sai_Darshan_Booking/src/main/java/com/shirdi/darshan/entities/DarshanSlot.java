package com.shirdi.darshan.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.shirdi.darshan.enums.DarshanTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "darshan_slots")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DarshanSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name; // Morning Darshan, Evening Aarti, etc.
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer maxCapacity;
    private Integer availableSlots;
    private Double price;
    
    @Enumerated(EnumType.STRING)
    private DarshanTypeEnum type; // REGULAR, VIP
    
    private Boolean isActive;
}

package com.shirdi.darshan.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aarti_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AartiType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String timings;

    @Column(nullable = false)
    private Double price;
    
    private Integer durationMinutes;
    
    @Column(length = 1000)
    private String guidelines;
    
    private Integer maxCapacity;

    @Column(nullable = false)
    private Boolean isActive = true;
}

package com.shirdi.darshan.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "darshan_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DarshanTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    private Double basePrice;
    
    private Boolean isActive = true;
}
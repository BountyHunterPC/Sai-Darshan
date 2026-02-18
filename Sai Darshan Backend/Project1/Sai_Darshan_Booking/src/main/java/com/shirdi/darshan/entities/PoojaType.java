package com.shirdi.darshan.entities;

import com.shirdi.darshan.enums.PoojaTypeEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pooja_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoojaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "pooja_code", nullable = false, unique = true)
    private PoojaTypeEnum poojaType;

    @Column(nullable = false)
    private String displayName; 
    // e.g. "Sai Abhishek Pooja"

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private Double price;

    @Column(length = 500)
    private String benefits;

    @Column(length = 500)
    private String requiredMaterials;

    @Column(nullable = false)
    private Boolean active = true;
}

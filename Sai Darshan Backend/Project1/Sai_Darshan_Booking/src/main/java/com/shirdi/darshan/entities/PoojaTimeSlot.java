package com.shirdi.darshan.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pooja_time_slots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoojaTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String slotTime;

    @Column(nullable = false)
    private Boolean isActive = true;
}

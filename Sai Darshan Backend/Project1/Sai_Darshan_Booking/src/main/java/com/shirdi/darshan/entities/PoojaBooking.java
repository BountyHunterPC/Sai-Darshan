package com.shirdi.darshan.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.shirdi.darshan.enums.BookingStatusEnum;
import com.shirdi.darshan.enums.GenderEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pooja_bookings")
@Getter
@Setter
public class PoojaBooking {

    /* =========================
       PRIMARY KEY
       ========================= */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* =========================
       REFERENCES
       ========================= */

    // Logged-in user who booked the pooja
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    // Selected pooja type (Sai Abhishek, Navagraha, etc.)
    @ManyToOne(optional = false)
    @JoinColumn(name = "pooja_type_id")
    private PoojaType poojaType;

    /* =========================
       BOOKING DETAILS
       ========================= */

    @Column(name = "pooja_date", nullable = false)
    private LocalDate poojaDate;

    @Column(name = "time_slot", nullable = false)
    private String timeSlot;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatusEnum status = BookingStatusEnum.CONFIRMED;

    /* =========================
       DEVOTEE DETAILS
       ========================= */

    @Column(name = "devotee_name", nullable = false)
    private String devoteeName;

    @Column(nullable = false)
    private Integer devoteeAge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderEnum devoteeGender;

    @Column(name = "devotee_mobile", nullable = false)
    private String devoteeMobile;

    @Column(name = "devotee_email")
    private String devoteeEmail;

    // Sankalp is mandatory as per UI
    @Column(nullable = false, length = 500)
    private String sankalp;

    // Optional fields
    private String gotra;

    @Column(length = 500)
    private String address;

    /* =========================
       AUDIT
       ========================= */

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}

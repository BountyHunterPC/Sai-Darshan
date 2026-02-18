package com.shirdi.darshan.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.data.annotation.CreatedDate;

import com.shirdi.darshan.enums.BookingStatusEnum;

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
@Table(name = "darshan_bookings")
@Getter
@Setter
public class DarshanBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "darshan_slot_id", nullable = false)
    private DarshanSlot darshanSlot;
    
    private LocalDate bookingDate;
    private Integer numberOfPeople;
    
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum status; // CONFIRMED, CANCELLED, COMPLETED
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}

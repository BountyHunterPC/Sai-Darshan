package com.shirdi.darshan.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.shirdi.darshan.enums.BookingTypeEnum;
import com.shirdi.darshan.enums.PaymentMethodEnum;
import com.shirdi.darshan.enums.PaymentStatusEnum;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingTypeEnum bookingType; // DARSHAN, AARTI, POOJA
    
    @Column(nullable = false)
    private Long bookingId; // ID of the specific booking
    
    @Column(nullable = false)
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum status; // PENDING, SUCCESS, FAILED
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodEnum paymentMethod; // CASH, CARD, UPI, ONLINE, FREE
    
    private String transactionId;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime paymentDate;
}

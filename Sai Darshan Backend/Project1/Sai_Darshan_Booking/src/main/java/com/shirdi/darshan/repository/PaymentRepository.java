package com.shirdi.darshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shirdi.darshan.entities.Payment;
import com.shirdi.darshan.enums.BookingTypeEnum;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByUserId(Long userId);
    
    List<Payment> findByBookingTypeAndBookingId(BookingTypeEnum bookingType, Long bookingId);
}

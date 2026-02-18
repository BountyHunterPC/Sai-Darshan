package com.shirdi.darshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shirdi.darshan.entities.AartiBooking;

public interface AartiBookingRepository extends JpaRepository<AartiBooking, Integer> {

    List<AartiBooking> findByUserId(int userId);
}

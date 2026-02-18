package com.shirdi.darshan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shirdi.darshan.entities.PoojaBooking;

public interface PoojaBookingRepository extends JpaRepository<PoojaBooking, Long> {

    List<PoojaBooking> findByUserIdOrderByCreatedAtDesc(Long userId);
}

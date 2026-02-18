package com.shirdi.darshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shirdi.darshan.entities.DarshanBooking;

import java.util.List;

public interface BookingRepository extends JpaRepository<DarshanBooking, Long> {
    List<DarshanBooking> findByUserId(Long userId);
}
package com.shirdi.darshan.repository;

import com.shirdi.darshan.entities.PoojaTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoojaTimeSlotRepository extends JpaRepository<PoojaTimeSlot, Long> {
    List<PoojaTimeSlot> findByIsActiveTrueOrderByIdAsc();
}

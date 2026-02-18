// DarshanSlotRepository.java
package com.shirdi.darshan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shirdi.darshan.entities.DarshanSlot;

import java.util.List;

public interface DarshanSlotRepository extends JpaRepository<DarshanSlot, Long> {
    List<DarshanSlot> findByIsActiveTrue();
}

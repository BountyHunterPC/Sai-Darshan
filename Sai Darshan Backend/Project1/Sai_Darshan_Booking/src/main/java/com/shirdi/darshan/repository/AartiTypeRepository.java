package com.shirdi.darshan.repository;

import com.shirdi.darshan.entities.AartiType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AartiTypeRepository extends JpaRepository<AartiType, Long> {
    List<AartiType> findByIsActiveTrue();
}

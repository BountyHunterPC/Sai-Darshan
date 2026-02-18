package com.shirdi.darshan.repository;

import com.shirdi.darshan.entities.DarshanTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DarshanTypeRepository extends JpaRepository<DarshanTypeEntity, Long> {
    List<DarshanTypeEntity> findByIsActiveTrue();
}
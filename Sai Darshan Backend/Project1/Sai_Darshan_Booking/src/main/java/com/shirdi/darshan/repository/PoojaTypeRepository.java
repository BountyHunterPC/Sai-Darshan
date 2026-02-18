package com.shirdi.darshan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shirdi.darshan.entities.PoojaType;
import com.shirdi.darshan.enums.PoojaTypeEnum;

public interface PoojaTypeRepository extends JpaRepository<PoojaType, Long> {

    List<PoojaType> findByActiveTrue();

    Optional<PoojaType> findByPoojaType(PoojaTypeEnum poojaType);
}

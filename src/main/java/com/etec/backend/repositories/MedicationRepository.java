package com.etec.backend.repositories;

import com.etec.backend.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByUserId(Long userId);
}

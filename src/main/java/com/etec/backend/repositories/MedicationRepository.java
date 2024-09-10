package com.etec.backend.repositories;

import com.etec.backend.dtos.MedicationConcatResponseDTO;
import com.etec.backend.dtos.PressureConcatResponseDTO;
import com.etec.backend.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByUserId(Long userId);

    @Query("SELECT new com.etec.backend.dtos.MedicationConcatResponseDTO(p.id, p.name, p.dosage, p.user.id, p.user.name, p.user.lastname, p.user.photo) "
            + "FROM Medication p JOIN p.user u")
    List<MedicationConcatResponseDTO> listarTodosComDadosUserDTO();
}

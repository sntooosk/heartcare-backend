package com.etec.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etec.backend.entities.Pressure;
import com.etec.backend.entities.dtos.PressureConcatResponseDTO;

import java.util.List;

public interface PressureRepository extends JpaRepository<Pressure, Long> {

    List<Pressure> findByUserId(Long userId);

    @Query("SELECT new com.etec.backend.entities.dtos.PressureConcatResponseDTO(p.id, p.diastolic, p.systolic, p.pulse, p.date, u.id, u.name, u.lastname, u.photo) "
            +
            "FROM Pressure p JOIN p.user u")
    List<PressureConcatResponseDTO> listarTodosComDadosUserDTO();

}

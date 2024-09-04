package com.etec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etec.backend.dto.PressureConcatResponseDTO;
import com.etec.backend.entity.Pressure;

import java.util.List;

public interface PressureRepository extends JpaRepository<Pressure, Long> {

    List<Pressure> findByUserId(Long userId);

    @Query("SELECT new com.etec.backend.dto.PressureConcatResponseDTO(p.id, p.diastolic, p.systolic, p.pulse, p.date, u.id, u.name, u.lastname, u.photo) "
            +
            "FROM Pressure p JOIN p.user u")
    List<PressureConcatResponseDTO> listarTodosComDadosUserDTO();

}

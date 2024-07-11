package com.etec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etec.backend.dto.PressureConcatResponseDTO;
import com.etec.backend.entity.Pressure;

import java.util.List;
import java.util.stream.Collectors;

public interface PressureRepository extends JpaRepository<Pressure, Long> {

    @Query(value = "SELECT * FROM tb02_pressure WHERE tb02_user_id = :userId", nativeQuery = true)
    List<Pressure> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT " +
            "tb02_pressure.tb02_id, " +
            "tb02_pressure.tb02_diastolic, " +
            "tb02_pressure.tb02_systolic, " +
            "tb02_pressure.tb02_pulse, " +
            "tb02_pressure.tb02_date, " +
            "tb00_users.tb00_id, " +
            "tb00_users.tb00_name, " +
            "tb00_users.tb00_lastname, " +
            "tb00_users.tb00_photo " +
            "FROM tb02_pressure " +
            "JOIN tb00_users ON tb02_pressure.tb02_user_id = tb00_users.tb00_id", nativeQuery = true)
    List<Object[]> listarTodosComDadosUser();

    default List<PressureConcatResponseDTO> listarTodosComDadosUserDTO() {
        List<Object[]> results = listarTodosComDadosUser();
        return results.stream()
                .map(PressureConcatResponseDTO::new)
                .collect(Collectors.toList());
    }

}

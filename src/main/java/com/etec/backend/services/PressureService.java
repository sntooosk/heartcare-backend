package com.etec.backend.services;

import com.etec.backend.entities.Pressure;
import com.etec.backend.entities.dtos.PressureConcatResponseDTO;
import com.etec.backend.entities.dtos.PressureResponseDTO;

import java.util.List;

public interface PressureService {

    List<PressureConcatResponseDTO> getAll();

    List<PressureResponseDTO> findByUserId(Long userId);

    Object create(Pressure pressure);

    Object update(Long id, Pressure pressure);

    Object delete(Long id);
}

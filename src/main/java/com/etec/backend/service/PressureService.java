package com.etec.backend.service;

import com.etec.backend.dto.PressureConcatResponseDTO;
import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.entity.Pressure;

import java.util.List;

public interface PressureService {

    List<PressureConcatResponseDTO> getAll();

    List<PressureResponseDTO> findByUserId(Long userId);

    Object create(Pressure pressure);

    Object update(Long id, Pressure pressure);

    Object delete(Long id);
}

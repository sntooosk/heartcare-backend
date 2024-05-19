package com.etec.backend.service;

import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.entity.Pressure;

import java.util.List;

public interface PressureService {
    
    List<PressureResponseDTO> findByUserId(Long userId);
    PressureResponseDTO create(Pressure pressure);
    PressureResponseDTO update(Long id, Pressure pressure);
    String delete(Long id);
}
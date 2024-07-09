
package com.etec.service;

import java.util.List;

import com.etec.dto.PressureResponseDTO;
import com.etec.entities.Pressure;

public interface PressureService {
    
    List<PressureResponseDTO> findByUserId(Integer userId);
    Object create(Pressure pressure);
    Object update(Integer id, Pressure pressure);
    Object delete(Integer id);
}

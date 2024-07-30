package com.etec.backend.service;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Pressure;

public interface PressureService {

    Object getAllConcat();
    Object findByUserId(Long userId);
    ResponseDTO create(Pressure pressure);
    ResponseDTO update(Long id, Pressure pressure);
    ResponseDTO delete(Long id);
}

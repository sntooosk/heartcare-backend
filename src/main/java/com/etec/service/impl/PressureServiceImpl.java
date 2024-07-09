
package com.etec.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.etec.dto.PressureResponseDTO;
import com.etec.entities.Pressure;
import com.etec.exceptions.ResponseDTO;
import com.etec.repositories.PressureRepository;
import com.etec.service.PressureService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PressureServiceImpl implements PressureService {

    private final PressureRepository pressureRepository;

    @Override
    public List<PressureResponseDTO> findByUserId(Integer userId) {
        List<Pressure> pressures = pressureRepository.findByUserId(userId);
        return pressures.stream()
                .map(pressure -> new PressureResponseDTO(pressure.getId(), pressure.getDiastolic(),
                        pressure.getSystolic(), pressure.getPulse(), pressure.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Object create(Pressure pressure) {
        Pressure savedPressure = pressureRepository.save(pressure);
        return new PressureResponseDTO(savedPressure.getId(), savedPressure.getDiastolic(), savedPressure.getSystolic(),
                savedPressure.getPulse(), savedPressure.getDate());
    }

    @Override
    public Object update(Integer id, Pressure pressure) {
        if (!pressureRepository.existsById(id)) {
            return new ResponseDTO("O ID especificado não existe: " + id);
        }
        pressure.setId(id);
        Pressure updatedPressure = pressureRepository.save(pressure);
        return new PressureResponseDTO(updatedPressure.getId(), updatedPressure.getDiastolic(),
                updatedPressure.getSystolic(), updatedPressure.getPulse(), updatedPressure.getDate());
    }

    @Override
    public Object delete(Integer id) {
        if (!pressureRepository.existsById(id)) {
            return new ResponseDTO("O ID especificado não existe: " + id);
        }
        pressureRepository.deleteById(id);
        return new ResponseDTO("O ID especificado foi removido com sucesso: " + id);
    }
}

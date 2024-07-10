package com.etec.backend.service.impl;

import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Post;
import com.etec.backend.entity.Pressure;
import com.etec.backend.repository.PressureRepository;
import com.etec.backend.service.PressureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PressureServiceImpl implements PressureService {

    private final PressureRepository pressureRepository;

    @Override
    public List<PressureResponseDTO> getAll() {
        List<Pressure> pressures = pressureRepository.findAll();
        return pressures.stream()
                .map(pressure -> new PressureResponseDTO(pressure.getId(), pressure.getDiastolic(),
                        pressure.getSystolic(), pressure.getPulse(), pressure.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PressureResponseDTO> findByUserId(Long userId) {
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
    public Object update(Long id, Pressure pressure) {
        if (!pressureRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }
        pressure.setId(id);
        Pressure updatedPressure = pressureRepository.save(pressure);
        return new PressureResponseDTO(updatedPressure.getId(), updatedPressure.getDiastolic(),
                updatedPressure.getSystolic(), updatedPressure.getPulse(), updatedPressure.getDate());
    }

    @Override
    public ResponseDTO delete(Long id) {
        if (!pressureRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
        }
        pressureRepository.deleteById(id);
        return new ResponseDTO("OK", "O ID especificado foi removido com sucesso: " + id);
    }
}

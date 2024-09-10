package com.etec.backend.services.impl;

import com.etec.backend.entities.Pressure;
import com.etec.backend.dtos.PressureConcatResponseDTO;
import com.etec.backend.dtos.PressureResponseDTO;
import com.etec.backend.dtos.ResponseDTO;
import com.etec.backend.repositories.PressureRepository;
import com.etec.backend.services.PressureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PressureServiceImpl implements PressureService {

    private final PressureRepository pressureRepository;

    @Override
    public List<PressureConcatResponseDTO> getAll() {
        return pressureRepository.listarTodosComDadosUserDTO();
    }

    @Override
    public List<PressureResponseDTO> findByUserId(Long userId) {
        List<Pressure> pressures = pressureRepository.findByUserId(userId);
        return pressures.stream()
                .map(pressure -> new PressureResponseDTO(
                        pressure.getId(),
                        pressure.getDiastolic(),
                        pressure.getSystolic(),
                        pressure.getPulse(),
                        pressure.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Object create(Pressure pressure) {
        Date newDate = new Date();
        pressure.setDate(newDate);
        try {
            Pressure savedPressure = pressureRepository.save(pressure);
            return new ResponseDTO("OK", "Pressão arterial criada com sucesso: " + savedPressure.getId());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao criar pressão arterial: " + e.getMessage());
        }
    }

    @Override
    public Object update(Long id, Pressure pressure) {
        try {
            if (!pressureRepository.existsById(id)) {
                return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
            }
            pressure.setId(id);
            Pressure updatedPressure = pressureRepository.save(pressure);
            return new ResponseDTO("OK", "Pressão arterial atualizada com sucesso: " + updatedPressure.getId());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao atualizar pressão arterial: " + e.getMessage());
        }
    }

    @Override
    public Object delete(Long id) {
        try {
            if (!pressureRepository.existsById(id)) {
                return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
            }
            pressureRepository.deleteById(id);
            return new ResponseDTO("OK", "Pressão arterial removida com sucesso.");
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao excluir pressão arterial: " + e.getMessage());
        }
    }
}
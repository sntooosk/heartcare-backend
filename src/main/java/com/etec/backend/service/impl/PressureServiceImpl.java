package com.etec.backend.service.impl;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.dto.PressureConcatResponseDTO;
import com.etec.backend.entity.Pressure;
import com.etec.backend.repository.PressureRepository;
import com.etec.backend.service.PressureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PressureServiceImpl implements PressureService {

    private final PressureRepository pressureRepository;

    @Override
    public Object getAllConcat() {
        List<PressureConcatResponseDTO> pressures = pressureRepository.listarTodosComDadosUserDTO();
        return pressures.isEmpty()
            ? new ResponseDTO("ERROR", "Nenhuma medição encontrada.")
            : pressures;
    }

    @Override
    public Object findByUserId(Long userId) {
        List<Pressure> pressures = pressureRepository.findByUserId(userId);
        return pressures.isEmpty()
            ? new ResponseDTO("ERROR", "Nenhuma medição para o usuário ID: " + userId)
            : pressures;
    }

    @Override
    public ResponseDTO create(Pressure pressure) {
        try {
            var savedPressure = pressureRepository.save(pressure);
            return new ResponseDTO("SUCCESS", "Medição criada, ID: " + savedPressure.getId());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao criar medição: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO update(Long id, Pressure pressure) {
        if (!pressureRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "ID não encontrado: " + id);
        }
        try {
            pressure.setId(id);
            pressureRepository.save(pressure);
            return new ResponseDTO("SUCCESS", "Atualizado com sucesso");
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao atualizar medição: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO delete(Long id) {
        if (!pressureRepository.existsById(id)) {
            return new ResponseDTO("ERROR", "ID não encontrado: " + id);
        }
        try {
            pressureRepository.deleteById(id);
            return new ResponseDTO("SUCCESS", "Medição removida com sucesso.");
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao excluir medição: " + e.getMessage());
        }
    }
}

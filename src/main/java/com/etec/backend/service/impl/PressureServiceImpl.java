package com.etec.backend.service.impl;

import com.etec.backend.dto.PressureConcatResponseDTO;
import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Pressure;
import com.etec.backend.repository.PressureRepository;
import com.etec.backend.service.PressureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PressureServiceImpl implements PressureService {

    private final PressureRepository pressureRepository;

    @Operation(summary = "Listar todas as medições de pressão arterial concatenadas", description = "Retorna uma lista de todas as medições de pressão arterial concatenadas.")
    @Override
    public List<PressureConcatResponseDTO> getAll() {
        return pressureRepository.listarTodosComDadosUserDTO();
    }

    @Operation(summary = "Listar medições de pressão arterial por ID de usuário", description = "Retorna uma lista de medições de pressão arterial com base no ID do usuário fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medições encontradas"),
            @ApiResponse(responseCode = "404", description = "Nenhuma medição encontrada para o usuário")
    })
    @Override
    public List<PressureResponseDTO> findByUserId(Long userId) {
        List<Pressure> pressures = pressureRepository.findByUserId(userId);
        return pressures.stream()
                .map(pressure -> new PressureResponseDTO(pressure.getId(), pressure.getDiastolic(),
                        pressure.getSystolic(), pressure.getPulse(), pressure.getDate()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Criar uma nova medição de pressão arterial", description = "Cria uma nova medição de pressão arterial com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medição criada com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PressureResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar medição")
    })
    @Override
    public Object create(Pressure pressure) {
        try {
            Pressure savedPressure = pressureRepository.save(pressure);
            return new PressureResponseDTO(savedPressure.getId(), savedPressure.getDiastolic(),
                    savedPressure.getSystolic(), savedPressure.getPulse(), savedPressure.getDate());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao criar pressão arterial: " + e.getMessage());
        }
    }

    @Operation(summary = "Atualizar uma medição de pressão arterial pelo ID", description = "Atualiza uma medição de pressão arterial existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medição atualizada com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PressureResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar medição"),
            @ApiResponse(responseCode = "404", description = "Medição não encontrada")
    })
    @Override
    public Object update(Long id, Pressure pressure) {
        try {
            if (!pressureRepository.existsById(id)) {
                return new ResponseDTO("ERROR", "O ID especificado não existe: " + id);
            }
            pressure.setId(id);
            Pressure updatedPressure = pressureRepository.save(pressure);
            return new PressureResponseDTO(updatedPressure.getId(), updatedPressure.getDiastolic(),
                    updatedPressure.getSystolic(), updatedPressure.getPulse(), updatedPressure.getDate());
        } catch (Exception e) {
            return new ResponseDTO("ERROR", "Erro ao atualizar pressão arterial: " + e.getMessage());
        }
    }

    @Operation(summary = "Excluir uma medição de pressão arterial pelo ID", description = "Exclui uma medição de pressão arterial existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medição excluída com sucesso", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Medição não encontrada")
    })
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

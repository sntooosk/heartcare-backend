package com.etec.backend.controller;

import com.etec.backend.dto.PressureConcatResponseDTO;
import com.etec.backend.dto.PressureResponseDTO;
import com.etec.backend.entity.Pressure;
import com.etec.backend.service.PressureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pressure", produces = "application/json")

@RequiredArgsConstructor
public class PressureController {

    private final PressureService pressureService;

    @Operation(summary = "Listar todas as medições de pressão arterial concatenadas", method = "GET", description = "Retorna uma lista de todas as medições de pressão arterial concatenadas.")
    @GetMapping("/")
    public List<PressureConcatResponseDTO> findByAllConcat() {
        return pressureService.getAll();
    }

    @Operation(summary = "Listar medições de pressão arterial por ID de usuário", method = "GET", description = "Retorna uma lista de medições de pressão arterial para o ID de usuário fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medições encontradas"),
            @ApiResponse(responseCode = "404", description = "Nenhuma medição encontrada para o usuário")
    })
    @GetMapping("/user/{userId}")
    public List<PressureResponseDTO> findByUserId(@PathVariable Long userId) {
        return pressureService.findByUserId(userId);
    }

    @Operation(summary = "Criar uma nova medição de pressão arterial", method = "POST", description = "Cria uma nova medição de pressão arterial com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medição criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar medição")
    })
    @PostMapping("/")
    public Object create(@RequestBody Pressure pressure) {
        return pressureService.create(pressure);
    }

    @Operation(summary = "Excluir medição de pressão arterial pelo ID", method = "DELETE", description = "Exclui a medição de pressão arterial com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medição excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Medição não encontrada")
    })
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return pressureService.delete(id);
    }

    @Operation(summary = "Atualizar medição de pressão arterial pelo ID", method = "PUT", description = "Atualiza a medição de pressão arterial com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medição atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar medição"),
            @ApiResponse(responseCode = "404", description = "Medição não encontrada")
    })
    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody Pressure pressure) {
        return pressureService.update(id, pressure);
    }
}

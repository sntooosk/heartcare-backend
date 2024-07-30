package com.etec.backend.controller;

import com.etec.backend.dto.LoginRequestDTO;
import com.etec.backend.dto.RegisterRequestDTO;
import com.etec.backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/auth", produces = "application/json")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Autenticar usuário", method = "POST", description = "Retorna o token de autenticação se o login for bem-sucedido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @PostMapping("/login")
    public Object login(@RequestBody LoginRequestDTO body) {
        return authService.login(body);
    }

    @Operation(summary = "Registrar usuário", method = "POST", description = "Cria uma nova conta de usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequestDTO body) {
        return authService.register(body);
    }
}

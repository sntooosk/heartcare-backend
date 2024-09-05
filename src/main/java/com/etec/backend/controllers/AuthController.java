package com.etec.backend.controllers;

import com.etec.backend.entities.dtos.LoginRequestDTO;
import com.etec.backend.entities.dtos.RegisterRequestDTO;
import com.etec.backend.services.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequestDTO body) {
        return authService.login(body);
    }

    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequestDTO body) {
        return authService.register(body);
    }
}

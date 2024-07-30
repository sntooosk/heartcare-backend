package com.etec.backend.controller;

import com.etec.backend.dto.LoginRequestDTO;
import com.etec.backend.dto.RegisterRequestDTO;
import com.etec.backend.service.AuthService;
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

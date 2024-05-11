package com.etec.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etec.backend.dto.LoginRequestDTO;
import com.etec.backend.dto.RegisterRequestDTO;
import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Auth;
import com.etec.backend.infra.security.TokenService;
import com.etec.backend.repository.AuthRepository;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        Auth auth = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), auth.getPassword())) {
            String token = this.tokenService.generateToken(auth);
            return ResponseEntity.ok(new ResponseDTO(auth.getName(), auth.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<Auth> auth = this.repository.findByEmail(body.email());

        if (auth.isEmpty()) {
            Auth newAuth = new Auth();
            newAuth.setPassword(passwordEncoder.encode(body.password()));
            newAuth.setEmail(body.email());
            newAuth.setName(body.name());
            this.repository.save(newAuth);

            String token = this.tokenService.generateToken(newAuth);
            return ResponseEntity.ok(new ResponseDTO(newAuth.getName(), newAuth.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
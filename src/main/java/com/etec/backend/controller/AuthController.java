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
import com.etec.backend.entity.User;
import com.etec.backend.infra.security.TokenService;
import com.etec.backend.repository.AuthRepository;
import com.etec.backend.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        Optional<Auth> authOptional = authRepository.findByEmail(body.email());
        if (authOptional.isPresent()) {
            Auth auth = authOptional.get();
            if (passwordEncoder.matches(body.password(), auth.getPassword())) {
                String token = tokenService.generateToken(auth);
                return ResponseEntity.ok(new ResponseDTO(auth.getUser().getId(),auth.getUser().getName(), auth.getEmail(), token));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<Auth> existingAuthOptional = authRepository.findByEmail(body.email());
        if (existingAuthOptional.isPresent()) {
            return ResponseEntity.badRequest().build(); // Email already registered
        }
        
        Auth newAuth = new Auth();
        newAuth.setEmail(body.email());
        newAuth.setPassword(passwordEncoder.encode(body.password()));
        authRepository.save(newAuth);

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setAuth(newAuth);
        userRepository.save(newUser);

        String token = tokenService.generateToken(newAuth);
        return ResponseEntity.ok(new ResponseDTO( newUser.getId(), newUser.getName(), newAuth.getEmail(), token));
    }
}

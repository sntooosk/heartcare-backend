package com.etec.backend.services.impl;

import com.etec.backend.entities.Auth;
import com.etec.backend.entities.User;
import com.etec.backend.dtos.AuthResponseDTO;
import com.etec.backend.dtos.LoginRequestDTO;
import com.etec.backend.dtos.RegisterRequestDTO;
import com.etec.backend.dtos.ResponseDTO;
import com.etec.backend.infra.security.TokenService;
import com.etec.backend.repositories.AuthRepository;
import com.etec.backend.repositories.UserRepository;
import com.etec.backend.services.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public Object login(LoginRequestDTO body) {
        Optional<Auth> authOptional = authRepository.findByEmail(body.email());
        if (authOptional.isPresent()) {
            Auth auth = authOptional.get();
            if (passwordEncoder.matches(body.password(), auth.getPassword())) {
                String token = tokenService.generateToken(auth);
                return new AuthResponseDTO(auth.getId(), auth.getUser().getName(), auth.getEmail(), token,
                        auth.getRole());
            } else {
                return new ResponseDTO("ERROR", "Senha incorreta.");
            }
        }
        return new ResponseDTO("ERROR", "E-mail não encontrado.");
    }

    @Override
    public Object register(RegisterRequestDTO body) {
        Optional<Auth> existingAuthOptional = authRepository.findByEmail(body.email());
        if (existingAuthOptional.isPresent()) {
            return new ResponseDTO("ERROR", "E-mail já em uso.");
        }

        Auth newAuth = new Auth();
        newAuth.setEmail(body.email());
        newAuth.setPassword(passwordEncoder.encode(body.password()));
        newAuth.setRole(body.role());
        String token = tokenService.generateToken(newAuth);
        authRepository.save(newAuth);

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setAuth(newAuth);
        userRepository.save(newUser);
        return new AuthResponseDTO(newAuth.getId(), newUser.getName(), newAuth.getEmail(), token, newAuth.getRole());
    }
}

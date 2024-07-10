package com.etec.backend.service.impl;

import com.etec.backend.dto.AuthResponseDTO;
import com.etec.backend.dto.LoginRequestDTO;
import com.etec.backend.dto.RegisterRequestDTO;
import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Auth;
import com.etec.backend.entity.User;
import com.etec.backend.infra.security.TokenService;
import com.etec.backend.repository.AuthRepository;
import com.etec.backend.repository.UserRepository;
import com.etec.backend.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                return new AuthResponseDTO(auth.getId(), auth.getUser().getName(), auth.getEmail(), token);
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
        String token = tokenService.generateToken(newAuth);
        authRepository.save(newAuth);

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setAuth(newAuth);
        userRepository.save(newUser);
        return new AuthResponseDTO(newAuth.getId(), newUser.getName(), newAuth.getEmail(), token);
    }
}

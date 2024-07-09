package com.etec.service;

import com.etec.dto.AuthResponse;
import com.etec.dto.LoginRequest;
import com.etec.dto.RegisterRequest;
import com.etec.entities.User;
import com.etec.entities.UserAccount;
import com.etec.entities.UserRole;
import com.etec.infra.security.JwtService;
import com.etec.infra.security.RefreshTokenService;
import com.etec.repositories.UserAccountRepository;
import com.etec.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final PasswordEncoder passwordEncoder;
        private final UserRepository userRepository;
        private final UserAccountRepository userAccountRepository;
        private final JwtService jwtService;
        private final RefreshTokenService refreshTokenService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterRequest registerRequest) {
                var user = User.builder()
                                .email(registerRequest.getEmail())
                                .password(passwordEncoder.encode(registerRequest.getPassword()))
                                .role(UserRole.USER)
                                .build();

                var userAccount = UserAccount.builder()
                                .name(registerRequest.getName())
                                .user(user)
                                .build();

                User savedUser = userRepository.save(user);
                userAccountRepository.save(userAccount);
                var accessToken = jwtService.generateToken(savedUser);
                var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

                return AuthResponse.builder()
                                .idUser(user.getId())
                                .accessToken(accessToken)
                                .refreshToken(refreshToken.getRefreshToken())
                                .build();
        }

        public AuthResponse login(LoginRequest loginRequest) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                loginRequest.getEmail(),
                                                loginRequest.getPassword()));

                var user = userRepository.findByEmail(loginRequest.getEmail())
                                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
                var accessToken = jwtService.generateToken(user);
                var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());

                return AuthResponse.builder()
                                .idUser(user.getId())
                                .accessToken(accessToken)
                                .refreshToken(refreshToken.getRefreshToken())
                                .build();
        }
}

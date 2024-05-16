package com.etec.backend.service;

import com.etec.backend.dto.AuthResponseDTO;
import com.etec.backend.dto.LoginRequestDTO;
import com.etec.backend.dto.RegisterRequestDTO;


public interface AuthService {
  AuthResponseDTO login(LoginRequestDTO body);

  AuthResponseDTO register(RegisterRequestDTO body);
}

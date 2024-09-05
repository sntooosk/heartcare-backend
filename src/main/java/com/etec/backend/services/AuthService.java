package com.etec.backend.services;

import com.etec.backend.entities.dtos.LoginRequestDTO;
import com.etec.backend.entities.dtos.RegisterRequestDTO;

public interface AuthService {
  Object login(LoginRequestDTO body);

  Object register(RegisterRequestDTO body);
}
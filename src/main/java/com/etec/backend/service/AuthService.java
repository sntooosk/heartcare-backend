package com.etec.backend.service;

import com.etec.backend.dto.LoginRequestDTO;
import com.etec.backend.dto.RegisterRequestDTO;

public interface AuthService {
  Object login(LoginRequestDTO body);

  Object register(RegisterRequestDTO body);
}

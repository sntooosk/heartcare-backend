package com.etec.backend.entities.dtos;

import com.etec.backend.entities.AuthRole;

public record RegisterRequestDTO(String name, String email, String password, AuthRole role) {
}

package com.etec.backend.dto;

import com.etec.backend.entity.AuthRole;

public record RegisterRequestDTO(String name, String email, String password , AuthRole role) {
}

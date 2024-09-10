package com.etec.backend.dtos;

import com.etec.backend.entities.AuthRole;

public record AuthResponseDTO(Long id, String name, String email, String token, AuthRole role) {
}

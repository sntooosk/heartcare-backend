package com.etec.backend.entities.dtos;

import com.etec.backend.entities.AuthRole;

public record AuthResponseDTO(Long id, String name, String email, String token, AuthRole role) {
}

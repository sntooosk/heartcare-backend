package com.etec.backend.dto;

import com.etec.backend.entity.AuthRole;

public record AuthResponseDTO(Long id , String name, String email, String token , AuthRole role) {
}

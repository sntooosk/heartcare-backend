package com.etec.backend.entities.dtos;

import lombok.Builder;

@Builder
public record EmailRequestDTO(String to, String from, String subject, String text) {
}

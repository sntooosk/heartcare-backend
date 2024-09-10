package com.etec.backend.dtos;

import lombok.Builder;

@Builder
public record EmailRequestDTO(String to, String from, String subject, String text) {
}

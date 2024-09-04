package com.etec.backend.dto;

import lombok.Builder;

@Builder
public record EmailRequestDTO(String to, String from, String subject, String text) {
}

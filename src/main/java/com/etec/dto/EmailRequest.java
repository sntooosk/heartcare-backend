package com.etec.dto;

import lombok.Builder;

@Builder
public record EmailRequest(String to, String subject, String text) {
}

package com.etec.backend.dtos;

public record ChatRequestDTO(Long senderId, Long recipientId, String content) {
}

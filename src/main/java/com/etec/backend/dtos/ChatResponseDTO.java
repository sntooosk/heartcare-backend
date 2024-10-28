package com.etec.backend.dtos;

import java.util.Date;

public record ChatResponseDTO(Long senderId, Long recipientId, String content, Date dateSender) {
}

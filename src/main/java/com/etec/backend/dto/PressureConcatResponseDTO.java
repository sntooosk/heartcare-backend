package com.etec.backend.dto;

import java.util.Date;

public record PressureConcatResponseDTO(
        Long id,
        String diastolic,
        String systolic,
        String pulse,
        Date date,
        Long userId,
        String userName,
        String userLastName,
        String userPhoto) {
}

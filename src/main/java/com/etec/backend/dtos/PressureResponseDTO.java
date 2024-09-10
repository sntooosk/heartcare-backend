package com.etec.backend.dtos;

import java.util.Date;

public record PressureResponseDTO(Long id, String diastolic, String systolic, String pulse, Date date) {
}

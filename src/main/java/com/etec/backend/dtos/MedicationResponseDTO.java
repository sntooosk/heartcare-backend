package com.etec.backend.dtos;

import java.util.Date;

public record MedicationResponseDTO(Long id, String name, String dosage , Date timeMedication) {
}

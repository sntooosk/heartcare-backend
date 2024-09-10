package com.etec.backend.dtos;


public record MedicationConcatResponseDTO(Long id, String name, String dosage,
                                        Long userId, String userName, String userLastName, String userPhoto) {
}

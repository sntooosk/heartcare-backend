package com.etec.backend.dto;

public record PressureConcatResponseDTO(Long id, String diastolic, String systolic, String pulse, String date,
        Long userId, String userName, String userLastName, String userPhoto) {
    public PressureConcatResponseDTO(Object[] tuple) {
        this(
                (Long) tuple[0],
                (String) tuple[1],
                (String) tuple[2],
                (String) tuple[3],
                (String) tuple[4],
                (Long) tuple[5],
                (String) tuple[6],
                (String) tuple[7],
                (String) tuple[8]);
    }
}

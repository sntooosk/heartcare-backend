package com.etec.backend.entities.dtos;

public record ChangePasswordRequestDTO(String password, String repeatPassword) {
}

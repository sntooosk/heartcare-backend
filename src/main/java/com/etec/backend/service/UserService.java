package com.etec.backend.service;

import com.etec.backend.dto.UserResponseDTO;
import com.etec.backend.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> list();

    UserResponseDTO listId(Long id);

    UserResponseDTO update(Long id, User user);
}

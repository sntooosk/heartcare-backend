package com.etec.backend.service;

import java.util.List;

import com.etec.backend.dto.UserResponseDTO;
import com.etec.backend.entity.User;

public interface UserService {

    List<UserResponseDTO> getAll();
    Object listId(Long id);
    Object update(Long id, User user);
    Object delete(Long id);

}

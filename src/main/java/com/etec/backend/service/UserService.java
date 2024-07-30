package com.etec.backend.service;

import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.User;

public interface UserService {

    Object getUserById(Long id);
    ResponseDTO updateUser(Long id, User user);

}

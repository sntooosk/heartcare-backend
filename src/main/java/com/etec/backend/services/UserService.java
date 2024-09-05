package com.etec.backend.services;

import com.etec.backend.entities.User;

public interface UserService {

    Object listId(Long id);

    Object update(Long id, User user);

}

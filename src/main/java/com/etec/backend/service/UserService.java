package com.etec.backend.service;


import com.etec.backend.entity.User;

public interface UserService {

    Object listId(Long id);
    Object update(Long id, User user);
    Object delete(Long id);

}

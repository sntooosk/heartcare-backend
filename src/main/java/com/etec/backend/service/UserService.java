package com.etec.backend.service;

import java.util.Optional;

import com.etec.backend.domain.user.User;

public interface UserService {

    User findById(String id);

    Optional<User> findByEmail(String email);

    User create(User user);

    User update(User user);

    void delete(String id);
}

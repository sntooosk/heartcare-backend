package com.etec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.backend.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}

package com.etec.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.backend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

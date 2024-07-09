package com.etec.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.backend.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
}

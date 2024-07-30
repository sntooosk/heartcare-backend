package com.etec.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.etec.backend.entity.Auth;

import jakarta.transaction.Transactional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Auth u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}

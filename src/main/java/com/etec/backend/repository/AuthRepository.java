package com.etec.backend.repository;

import com.etec.backend.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    
    Optional<Auth> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Auth a SET a.password = ?2 WHERE a.email = ?1")
    void updatePassword(String email, String password);
}

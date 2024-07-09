package com.etec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}

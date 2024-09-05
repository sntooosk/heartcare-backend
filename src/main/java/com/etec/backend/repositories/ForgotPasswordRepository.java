package com.etec.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etec.backend.entities.Auth;
import com.etec.backend.entities.ForgotPassword;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.auth = ?2")
    Optional<ForgotPassword> findByOtpAndUser(Long otp, Auth auth);
}

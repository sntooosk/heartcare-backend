package com.etec.backend.repository;

import com.etec.backend.entity.Auth;
import com.etec.backend.entity.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

    @Query("SELECT fp FROM ForgotPassword fp WHERE fp.otp = ?1 AND fp.auth = ?2")
    Optional<ForgotPassword> findByOtpAndAuth(Long otp, Auth auth);
}

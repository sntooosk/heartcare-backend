package com.etec.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tb04_forgot_password")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb04_id")
    private Long id;

    @Column(name = "tb04_otp", nullable = false)
    private Long otp;

    @Column(name = "tb04_expiration_time", nullable = false)
    private Date expirationTime;

    @ManyToOne
    @JoinColumn(name = "tb04_user_id")
    private Auth auth;
}

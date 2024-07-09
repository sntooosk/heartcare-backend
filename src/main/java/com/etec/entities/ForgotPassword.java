package com.etec.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tb03_forgot_password")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb03_id")
    private Integer id;

    @Column(name = "tb03_otp", nullable = false)
    private Integer otp;

    @Column(name = "tb03_expiration_time", nullable = false)
    private Date expirationTime;

    @OneToOne
    @JoinColumn(name = "tb03_users_id")
    private User user;
}

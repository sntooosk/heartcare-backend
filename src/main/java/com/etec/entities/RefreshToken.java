package com.etec.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "tb02_refresh_token")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb02_id")
    private Integer id;

    @Column(name = "tb02_refresh_token", nullable = false, length = 500)
    @NotBlank(message = "Por favor, insira o valor do token de atualização!")
    private String refreshToken;

    @Column(name = "tb02_expiration_time", nullable = false)
    private Instant expirationTime;

    @OneToOne
    @JoinColumn(name = "tb02_users_id")
    private User user;
}

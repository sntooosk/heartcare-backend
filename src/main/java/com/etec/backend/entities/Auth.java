package com.etec.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb01_auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb01_id")
    private Long id;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    @Column(name = "tb01_email", unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Column(name = "tb01_password")
    private String password;

    @OneToOne(mappedBy = "auth")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "tb01_role")
    private AuthRole role;
}

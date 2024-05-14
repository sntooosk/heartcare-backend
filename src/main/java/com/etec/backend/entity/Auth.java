package com.etec.backend.entity;


import jakarta.persistence.*;
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

    @Column(name = "tb01_email")
    private String email;

    @Column(name = "tb01_password")
    private String password;

    @OneToOne(mappedBy = "auth")
    private User user;
}

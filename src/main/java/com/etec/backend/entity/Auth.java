package com.etec.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb00_auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tb00_id")
    private String id;

    @Column(name = "tb00_name")
    private String name;

    @Column(name = "tb00_email")
    private String email;

    @Column(name = "tb00_password")
    private String password;
}

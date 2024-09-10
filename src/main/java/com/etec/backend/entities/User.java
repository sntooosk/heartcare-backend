package com.etec.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb00_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb00_id")
    private Long id;

    @Column(name = "tb00_name")
    private String name;

    @Column(name = "tb00_lastname")
    private String lastname;

    @Column(name = "tb00_dob")
    private String dob;

    @Column(name = "tb00_gender")
    private String gender;

    @Column(name = "tb00a_photo")
    private String photo;

    @OneToOne
    @JoinColumn(name = "tb00_auth_id")
    private Auth auth;
}

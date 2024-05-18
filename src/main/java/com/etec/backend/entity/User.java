package com.etec.backend.entity;


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
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb00_id")
    private Long id;

    @Column(name = "tb00_name")
    private String name;

    @Column(name = "tb00_lastname")
    private String lastName;

    @Column(name = "tb00_dob")
    private String dob;

    @Column(name = "tb00_gender")
    private String gender;

    @Lob
    @Column(name = "tb00_photo", columnDefinition = "BYTEA")
    private Byte[] photo;

    @OneToOne
    @JoinColumn(name = "tb00_auth_id")
    private Auth auth;
}

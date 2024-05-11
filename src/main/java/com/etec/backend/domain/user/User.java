package com.etec.backend.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb01_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tb01_id")
    private String id;

    @Column(name = "tb01_name")
    private String name;

    @Column(name = "tb01_last_name")
    private String lastName;

    @Column(name = "tb01_dob")
    private String dob;

    @Column(name = "tb01_number")
    private String number;

    @Column(name = "tb01_gender")
    private String gender;

    @Column(name = "tb01_photo")
    private String photo;

    @Column(name = "tb01_email")
    private String email;

    @Column(name = "tb01_password")
    private String password;
}

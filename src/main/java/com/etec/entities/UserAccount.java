package com.etec.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb04_users_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb04_id")
    private Integer id;

    @Column(name = "tb04_name")
    private String name;

    @Column(name = "tb04_lastname")
    private String lastname;

    @Column(name = "tb04_dob")
    private String dob;

    @Column(name = "tb04_gender")
    private String gender;

    @Column(name = "tb04_photo")
    private String photo;

    @OneToOne
    @JoinColumn(name = "tb04_users_id")
    private User user;
}

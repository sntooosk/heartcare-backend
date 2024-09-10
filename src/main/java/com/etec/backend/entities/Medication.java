package com.etec.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb05_medication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb05_id")
    private Long id;

    @Column(name = "tb05_name")
    private String name;

    @Column(name = "tb05_dosage")
    private String dosage;

    @Column(name = "tb05_date_medication")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMedication;

    @ManyToOne
    @JoinColumn(name = "tb05_user_id")
    private User user;
}

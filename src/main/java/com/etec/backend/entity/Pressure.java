package com.etec.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb02_pressure")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pressure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb02_id")
    private Long id;

    @Column(name = "tb02_diastolic")
    private String diastolic;

    @Column(name = "tb02_systolic")
    private String systolic;

    @Column(name = "tb02_pulse")
    private String pulse;

    @Column(name = "tb02_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "tb02_user_id")
    private User user;
}

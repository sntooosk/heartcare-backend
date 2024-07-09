package com.etec.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb05_pressure")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pressure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb05_id")
    private Integer id;

    @Column(name = "tb05_diastolic")
    private String diastolic;

    @Column(name = "tb05_systolic")
    private String systolic;

    @Column(name = "tb05_pulse")
    private String pulse;

    @Column(name = "tb05_date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "tb05_user_id")
    private UserAccount userAccount;
}

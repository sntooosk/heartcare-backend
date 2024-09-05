package com.etec.backend.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb03_posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb03_id")
    private Long id;

    @Column(name = "tb03_title")
    private String title;

    @Column(name = "tb03_comment")
    private String comment;

    @Column(name = "tb03_date")
    private Date date;

}

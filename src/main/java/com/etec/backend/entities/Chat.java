package com.etec.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb06_chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb06_id")
    private Long id;

    @Column(name = "tb06_sender_id", nullable = false)
    private Long senderId;

    @Column(name = "tb06_recipient_id", nullable = false)
    private Long recipientId;

    @Column(name = "tb06_content", nullable = false)
    private String content;

    @Column(name = "tb06_date_sender", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSender;
}

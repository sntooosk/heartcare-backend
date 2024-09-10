package com.etec.backend.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.etec.backend.dtos.EmailRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;


    public void sendSimpleMessage(EmailRequestDTO EmailRequestDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(EmailRequestDTO.to());
        message.setFrom(EmailRequestDTO.from());
        message.setSubject(EmailRequestDTO.subject());
        message.setText(EmailRequestDTO.text());

        javaMailSender.send(message);
    }
}

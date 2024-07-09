package com.etec.service.impl;


import com.etec.dto.EmailRequest;
import com.etec.service.EmailService;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements  EmailService{

    private final JavaMailSender javaMailSender;

    public void sendSimpleMessage(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.to());
        message.setFrom("Juliano.santos88@icloud.com");
        message.setSubject(emailRequest.subject());
        message.setText(emailRequest.text());

        javaMailSender.send(message);
    }
}

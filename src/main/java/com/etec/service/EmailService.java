package com.etec.service;

import com.etec.dto.EmailRequest;

public interface EmailService {

    void sendSimpleMessage(EmailRequest emailRequest);

}

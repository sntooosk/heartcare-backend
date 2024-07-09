package com.etec.service.impl;

import com.etec.dto.EmailRequest;
import com.etec.entities.ForgotPassword;
import com.etec.entities.User;
import com.etec.exceptions.ResponseDTO;
import com.etec.repositories.ForgotPasswordRepository;
import com.etec.repositories.UserRepository;
import com.etec.service.EmailService;
import com.etec.service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Object verifyEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Por favor, forneça um email válido: " + email));

        int otp = otpGenerator();
        EmailRequest emailRequest = EmailRequest.builder()
                .to(email)
                .text("Este é o OTP para sua solicitação de redefinição de senha: " + otp)
                .subject("OTP para solicitação de redefinição de senha")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 20 * 1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(emailRequest);
        forgotPasswordRepository.save(fp);

        return new ResponseDTO("Email enviado para verificação!");
    }

    @Override
    public Object verifyOtp(Integer otp, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Por favor, forneça um email válido!"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new UsernameNotFoundException("OTP inválido para o email: " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getId());
            return new ResponseDTO("OTP expirou!");
        }

        return new ResponseDTO("OTP verificado!");
    }

    @Override
    public Object changePassword(String email, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        userRepository.updatePassword(email, encodedPassword);

        return new ResponseDTO("Senha alterada com sucesso!");
    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}

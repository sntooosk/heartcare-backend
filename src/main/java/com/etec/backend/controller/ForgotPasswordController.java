package com.etec.backend.controller;

import com.etec.backend.dto.ChangePasswordRequestDTO;
import com.etec.backend.dto.EmailRequestDTO;
import com.etec.backend.entity.Auth;
import com.etec.backend.entity.ForgotPassword;
import com.etec.backend.repository.AuthRepository;
import com.etec.backend.repository.ForgotPasswordRepository;
import com.etec.backend.utils.EmailService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final AuthRepository authRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    // enviar email para verificação
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        Auth auth = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Por favor, forneça um email válido: " + email));

        Long otp = otpGenerator();
        EmailRequestDTO emailRequestDTO = EmailRequestDTO.builder()
                .to(email)
                .text("Este é o OTP para sua solicitação de redefinição de senha: " + otp)
                .subject("OTP para solicitação de redefinição de senha")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 1 * 60 * 1000)) // 4 minutos
                .auth(auth)
                .build();

        emailService.sendSimpleMessage(emailRequestDTO);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("Email enviado para verificação!");
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Long otp, @PathVariable String email) {
        Auth auth = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Por favor, forneça um email válido!"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, auth)
                .orElseThrow(() -> new RuntimeException("OTP inválido para o email: " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getId());
            return new ResponseEntity<>("OTP expirou!", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("OTP verificado!");
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO,
            @PathVariable String email) {
        if (!Objects.equals(changePasswordRequestDTO.password(), changePasswordRequestDTO.repeatPassword())) {
            return new ResponseEntity<>("Por favor, digite a senha novamente!", HttpStatus.EXPECTATION_FAILED);
        }

        String encodedPassword = passwordEncoder.encode(changePasswordRequestDTO.password());
        authRepository.updatePassword(email, encodedPassword);

        return ResponseEntity.ok("Senha alterada com sucesso!");
    }

    private Long otpGenerator() {
        Random random = new Random();
        return random.nextLong(100_000, 999_999);
    }
}

package com.etec.backend.controller;

import com.etec.backend.dto.ChangePasswordRequestDTO;
import com.etec.backend.dto.EmailRequestDTO;
import com.etec.backend.dto.ResponseDTO;
import com.etec.backend.entity.Auth;
import com.etec.backend.entity.ForgotPassword;
import com.etec.backend.repository.AuthRepository;
import com.etec.backend.repository.ForgotPasswordRepository;
import com.etec.backend.utils.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping(value = "/forgotPassword", produces = "application/json")

@RequiredArgsConstructor
public class ForgotPasswordController {

    private final AuthRepository authRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Enviar email de verificação para redefinição de senha", method = "POST", description = "Envia um email com um OTP para verificar a redefinição de senha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email enviado com sucesso para verificação"),
            @ApiResponse(responseCode = "400", description = "Erro ao enviar email")
    })
    @PostMapping("/verifyMail/{email}")
    public ResponseDTO verifyEmail(@PathVariable String email) {
        Optional<Auth> authOptional = authRepository.findByEmail(email);

        if (authOptional.isEmpty()) {
            return new ResponseDTO("ERROR", "Por favor, forneça um email válido");
        }

        Auth auth = authOptional.get();
        Long otp = otpGenerator();
        EmailRequestDTO emailRequestDTO = EmailRequestDTO.builder()
                .to(email)
                .from("heartcaresoftwares@gmail.com")
                .text("Este é o OTP para sua solicitação de redefinição de senha: " + otp)
                .subject("OTP para solicitação de redefinição de senha")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 1 * 60 * 1000)) // 1 minuto
                .auth(auth)
                .build();

        emailService.sendSimpleMessage(emailRequestDTO);
        forgotPasswordRepository.save(fp);

        return new ResponseDTO("OK", "Email enviado para verificação!");
    }

    @Operation(summary = "Verificar OTP para redefinição de senha", method = "POST", description = "Verifica se o OTP enviado é válido para redefinição de senha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP verificado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na verificação do OTP")
    })
    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseDTO verifyOtp(@PathVariable Long otp, @PathVariable String email) {
        Optional<Auth> authOptional = authRepository.findByEmail(email);

        if (authOptional.isEmpty()) {
            return new ResponseDTO("ERROR", "Por favor, forneça um email válido!");
        }

        Auth auth = authOptional.get();
        Optional<ForgotPassword> fpOptional = forgotPasswordRepository.findByOtpAndUser(otp, auth);

        if (fpOptional.isEmpty()) {
            return new ResponseDTO("ERROR", "OTP inválido para o email: " + email);
        }

        ForgotPassword fp = fpOptional.get();

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getId());
            return new ResponseDTO("ERROR", "OTP expirou!");
        }

        return new ResponseDTO("OK", "OTP verificado com sucesso!");
    }

    @Operation(summary = "Alterar senha após verificação do OTP", method = "POST", description = "Altera a senha do usuário após a verificação bem-sucedida do OTP.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar senha")
    })
    @PostMapping("/changePassword/{email}")
    public ResponseDTO changePasswordHandler(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO,
                                             @PathVariable String email) {
        if (!Objects.equals(changePasswordRequestDTO.password(), changePasswordRequestDTO.repeatPassword())) {
            return new ResponseDTO("ERROR", "Por favor, digite a senha novamente!");
        }

        String encodedPassword = passwordEncoder.encode(changePasswordRequestDTO.password());
        authRepository.updatePassword(email, encodedPassword);

        return new ResponseDTO("OK", "Senha alterada com sucesso!");
    }

    private Long otpGenerator() {
        Random random = new Random();
        return random.nextLong(100_000, 999_999);
    }
}

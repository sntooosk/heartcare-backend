package com.etec.controllers;

import com.etec.dto.ChangePassword;
import com.etec.service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgotPassword")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/verifyMail/{email}")
    public Object verifyEmail(@PathVariable String email) {
        return forgotPasswordService.verifyEmail(email);
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public Object verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        return forgotPasswordService.verifyOtp(otp, email);
    }

    @PostMapping("/changePassword/{email}")
    public Object changePasswordHandler(@RequestBody ChangePassword changePassword,
            @PathVariable String email) {
        return forgotPasswordService.changePassword(email, changePassword.password());
    }
}

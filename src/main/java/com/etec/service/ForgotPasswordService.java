package com.etec.service;

public interface ForgotPasswordService {

    Object verifyEmail(String email);

    Object verifyOtp(Integer otp, String email);

    Object changePassword(String email, String newPassword);
}

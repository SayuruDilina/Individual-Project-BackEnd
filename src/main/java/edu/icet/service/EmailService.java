package edu.icet.service;

public interface EmailService {
    void sendMail(String employeeName, String email);

    void sendMailToNewUser(String userName, String email);

    void sendOtp(String email);
    boolean  verifyOtp(String otp,String password);
}

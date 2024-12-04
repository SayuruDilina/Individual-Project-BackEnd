package edu.icet.service.impl;

import edu.icet.repository.UserRepository;
import edu.icet.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
        private String generateOtp;
        private String resetPWEmail;

    private String generateOtp(){

        Random random=new Random();
        int otp= random.nextInt(999999);
        generateOtp=String.format("%06d",otp);
        return generateOtp;
    }

    @Override
    public void sendMail(String employeeName, String email) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Welcome To Our SCHLOSSBERG Family");
        message.setText(" We warmly welcome"+employeeName+",\n\n Thank You for join with us and  I wish your new job");
        message.setFrom("tumiraduli@gmail.com");
        javaMailSender.send(message);
    }

    @Override
    public void sendMailToNewUser(String userName, String email) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Welcome To Our SCHLOSSBERG Family");
        message.setText(" We warmly welcome"+userName+",\n\n Thank You for join with us and  shop with us.Enjoy YOur Day");
        message.setFrom("tumiraduli@gmail.com");
        javaMailSender.send(message);
    }

    @Override
    public void sendOtp(String email) {
        String otp=generateOtp();
        resetPWEmail=email;
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("tumiraduli@gmail.com");
        message.setTo(email);
        message.setSubject("OTP");
        message.setText("Your OTP is:"+otp);
        javaMailSender.send(message);
    }
        @Override
    public boolean verifyOtp(String otp,String password){
            if(generateOtp.equals(otp)){
                userRepository.resetPassword(resetPWEmail,password);
            return true;
            }
            else{
                return false;
            }

    }

}
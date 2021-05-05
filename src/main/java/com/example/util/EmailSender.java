package com.example.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(String message, String sendTo){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("chenguihong2@qq.com");
        mailMessage.setTo(sendTo);
        mailMessage.setSubject("注册通知");
        mailMessage.setText(message);
        mailSender.send(mailMessage);
        }
}

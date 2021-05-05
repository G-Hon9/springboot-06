package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class Springboot06ApplicationTests {

    @Autowired
    JavaMailSender mailSender;


    @Test
    void contextLoads() {
        /*//封装简单的邮件内容
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发件⼈
        mailMessage.setFrom("chenguihong2@qq.com");
        //收件⼈
        mailMessage.setTo("2734335694@qq.com");
        //主题
        mailMessage.setSubject("放假通知");
        //内容
        mailMessage.setText("2022年春节放假20天");
        mailSender.send(mailMessage);*/
    }

}

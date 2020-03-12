package com.qf.my.project.email.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class MyProjectEmailServiceApplicationTests {

    @Autowired
    private JavaMailSender sender;

    @Test
    void contextLoads() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("测试邮件主题-nz1905");
        mailMessage.setText("测试邮件内容-nz1905");
        mailMessage.setFrom("214490523@qq.com");
        mailMessage.setTo("hzjavatestmail@sina.com");
        sender.send(mailMessage);



    }

}

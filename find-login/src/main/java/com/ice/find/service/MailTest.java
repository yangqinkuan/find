package com.ice.find.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 012466770 ON 2018/11/15.
 */
@RestController
public class MailTest {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/send")
    public String Send(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("find_mailsender@163.com");
        message.setTo("610628199@qq.com");
        message.setSubject("测试邮件");
        message.setText("你好啊");
        mailSender.send(message);
        System.out.println("发送成功");
        return "success";
    }
}

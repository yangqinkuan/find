/**
 * FileName: EmailServiceImpl
 * Author:   yangqinkuan
 * Date:     2018-11-27 17:16
 * Description: 实现邮件服务接口
 */

package com.ice.find.mail.service.impl;

import com.ice.find.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Component
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();//创建简单邮件信息
        message.setFrom(from);//设置发送人
        message.setTo(to);//设置收件人
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息

        try {
            //true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendAttachmentsEmail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);//true表示这个邮件是有附件的

            FileSystemResource file = new FileSystemResource(new File(filePath));//创建文件系统资源
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,file);//添加附件

            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendInlineResourceEmail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));

            //添加内联资源， 一个id 对应一个资源，最终通过id来找到该资源
            helper.addInline(rscId, res);//添加多个图片可以使用多条 <img src='cid:"+ rscId + "'> 和 helper.addInline(rscId, res) 来实现

            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTemplateMail(String to, String subject, Map<String, String> map) {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("username","yangqinkuan");
        String emmailContent = templateEngine.process("email",context);

        System.out.println(emmailContent);
        this.sendHtmlEmail(to,subject,emmailContent);
    }
}

/**
 * Copyright (C), 2018-2019,
 * FileName: UserMail
 * Author:   Administrator
 * Date:     2019/1/19 0:54
 * Description: 处理用户相关邮件
 */
package com.ice.find.mail.mq.handler;

import com.alibaba.fastjson.JSON;
import com.ice.find.mail.mq.handler.mannager.MailHandler;
import com.ice.find.mail.mq.handler.mannager.UserMailHandler;
import com.ice.find.mail.service.impl.EmailServiceImpl;
import com.ice.find.registry.dto.email.EmailRegistryDto;
import com.ice.find.util.kafka.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMailHandlerImpl extends MailHandler implements UserMailHandler {
    @Autowired
    private EmailServiceImpl emailServiceImpl;


    public void handle(KafkaMessage kafkaMessage){
        switch (kafkaMessage.getEventTypeID()){
            case "20001":
                sendVaildCode(kafkaMessage);
                break;
            case "2":
                break;
            default:
                break;
        }
    }
    @Override
     public void sendVaildCode(KafkaMessage kafkaMessage){
        EmailRegistryDto emailRegistryDto = JSON.parseObject((kafkaMessage).getPayload(),EmailRegistryDto.class);
        emailServiceImpl.sendSimpleEmail(emailRegistryDto.getEmail(),"注册验证码",emailRegistryDto.getValidCode());
    }
}
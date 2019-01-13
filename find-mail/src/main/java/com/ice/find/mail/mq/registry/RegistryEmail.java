/**
 * FileName: RegistryEmail
 * Author:   yangqinkuan
 * Date:     2019-1-13 18:17
 * Description: 用于接受注册邮件
 */

package com.ice.find.mail.mq.registry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ice.find.mail.service.impl.EmailServiceImpl;
import com.ice.find.registry.dto.email.EmailRegistryDto;
import com.ice.find.util.kafka.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegistryEmail {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @KafkaListener(topics = {"registry_mail"})
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            KafkaMessage kafkaMessage = (KafkaMessage)message.get();
            System.out.println("===============收到一条消息"+ JSONObject.toJSON(kafkaMessage));
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
    }


    private void sendVaildCode(KafkaMessage kafkaMessage){
        EmailRegistryDto emailRegistryDto = JSON.parseObject((kafkaMessage).getPayload(),EmailRegistryDto.class);
        emailServiceImpl.sendSimpleEmail(emailRegistryDto.getEmail(),"注册验证码",emailRegistryDto.getValidCode());
    }
}


/**
 * FileName: RegistryEmail
 * Author:   yangqinkuan
 * Date:     2019-1-13 18:17
 * Description: 用于接受注册邮件
 */

package com.ice.find.mail.mq.receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ice.find.mail.mq.handler.UserMailHandlerImpl;
import com.ice.find.util.kafka.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MqRecerver {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMailHandlerImpl userMailHandlerImpl;

    @KafkaListener(topics = {"user_mail"})
    public void listen(ConsumerRecord<?,?> record){
        logger.info("邮箱发送请求消息"+record.toString());
        Optional<?> message = Optional.ofNullable(record.value());
        if(message.isPresent()){
            String msg = message.get().toString();
            KafkaMessage kafkaMessage = JSON.parseObject(msg,KafkaMessage.class);
            userMailHandlerImpl.handle(kafkaMessage);

        }
    }



}


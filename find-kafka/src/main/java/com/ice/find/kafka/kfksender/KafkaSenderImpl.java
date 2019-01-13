/**
 * Copyright (C), 2018-2019,
 * FileName: KafkaSenderImpl
 * Author:   Administrator
 * Date:     2019/1/5 23:29
 * Description: kafka消息发送
 */
package com.ice.find.kafka.kfksender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;


@Component
public class KafkaSenderImpl {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(){
        String msg = "hello";
        kafkaTemplate.send("test",msg);
    }

}
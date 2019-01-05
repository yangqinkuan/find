/**
 * Copyright (C), 2018-2019,
 * FileName: KafkaListenerImpl
 * Author:   Administrator
 * Date:     2019/1/5 23:36
 * Description: kafka消息监听
 */
package com.ice.find.kafka.kfklisener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaListenerImpl {
    @KafkaListener(topics = {"test"})
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> kafkamessage = Optional.ofNullable(record.value());
        if(kafkamessage.isPresent()){
            Object msg = kafkamessage.get();
            System.out.println("===============收到一条消息"+msg);
        }
    }
}
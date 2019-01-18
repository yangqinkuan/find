/**
 * Copyright (C), 2018-2019,
 * FileName: UserMailHandler
 * Author:   Administrator
 * Date:     2019/1/19 1:08
 * Description: 定义用户邮箱处理接口
 */
package com.ice.find.mail.mq.handler.mannager;

import com.ice.find.util.kafka.KafkaMessage;

public interface UserMailHandler {
    void sendVaildCode(KafkaMessage kafkaMessage);
}
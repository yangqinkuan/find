/**
 * FileName: KafkaMessage
 * Author:   yangqinkuan
 * Date:     2019-1-13 17:44
 * Description: 用于传递kafka消息
 */

package com.ice.find.util.kafka;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class KafkaMessage implements Serializable {
    private static final long serialVersionUID = 4483698179846699970L;


    /**
     * 事件类型id
     */
    private String eventTypeID;
    /**
     * 指定唯一消息的ID
     */
    private String messageID;

    /**
     * 消息内容主体
     */
    private String payload;

    public String getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}

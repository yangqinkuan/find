/**
 * FileName: BusenessMessage
 * Author:   yangqinkuan
 * Date:     2019-3-20 15:16
 * Description: 服务器业务消息
 */

package com.ice.find.message;

import java.io.Serializable;

public class BusenessMessage implements Serializable {
    private static final long serialVersionUID = -3750017766938211408L;
    private String eventType;
    private String messageId;
    private Object body;
    private String instansId;

    public String getInstansId() {
        return instansId;
    }

    public void setInstansId(String instansId) {
        this.instansId = instansId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}

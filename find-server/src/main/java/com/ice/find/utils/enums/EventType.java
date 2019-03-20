/**
 * FileName: EventType
 * Author:   yangqinkuan
 * Date:     2019-3-20 16:11
 * Description:
 */

package com.ice.find.utils.enums;

public enum EventType {
    CON_REQ("00001","握手请求"),
    CON_ACK("00002","握手响应"),
    PING("00003","心跳请求"),
    PONG("00004","心跳响应"),
    LOGIN_REQ("10001","登录请求"),
    LOGIN_ACK("10002","登录响应"),

    ;

    private String eventType;
    private String description;

    EventType(String eventType, String description) {
        this.eventType = eventType;
        this.description = description;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

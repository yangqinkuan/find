/**
 * FileName: EventTypeEnum
 * Author:   yangqinkuan
 * Date:     2019-1-13 17:48
 * Description: 事件类型枚举
 */

package com.ice.find.util.common.enums;

public enum EventTypeEnum {

    /**
     * 2xxxx代表用户模块类事件,如注册，登陆，找回密码等
     *
     *
     * */


    /* 2xxxx*/
    REGISTRY_MAIL_SEND("20001","邮箱注册事件"),


    ;















    private String eventTypeID;
    private String description;

    public String getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    EventTypeEnum(String eventTypeID, String description){
        this.eventTypeID = eventTypeID;
        this.description = description;
    }
}

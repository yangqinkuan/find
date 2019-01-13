/**
 * FileName: KafkaTopicEnum
 * Author:   yangqinkuan
 * Date:     2019-1-13 18:06
 * Description: kafka主题枚举
 */

package com.ice.find.util.enums;

public enum KafkaTopicEnum {
    /**
     * 2xxxx代表用户模块类事件,如注册，登陆，找回密码等
     *
     *
     * */


    /* 2xxxx*/
    REGISTRY_MAIL_SEND("registry_mail","20001"),
    ;
    public static String getTopicByEventTypeID(String eventTypeID){
        for(KafkaTopicEnum e:KafkaTopicEnum.values()){
            if(e.getEventTypeID().equals(eventTypeID)){
                return e.getTopic();
            }
        }
        return null;
    }
















    private String topic;
    private String eventTypeID;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(String eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    KafkaTopicEnum(String topic, String eventTypeID) {
        this.topic = topic;
        this.eventTypeID = eventTypeID;
    }
}

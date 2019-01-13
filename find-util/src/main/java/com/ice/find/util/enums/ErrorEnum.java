/**
 * FileName: ErrorEnum
 * Author:   yangqinkuan
 * Date:     2019-1-13 15:05
 * Description: 错误类型枚举
 */

package com.ice.find.util.enums;

public enum ErrorEnum {
    /**
     * 错误枚举类
     * 1xxxx代表插件使用错误如 redis kafka 等
     * 2xxxx代表用户模块错误,如注册，登陆，找回密码等
     *
     *
     * */
    SUCCESS("00000","成功"),
    PARAMS_ERROR("00001","参数错误"),
    ERROR("99999","服务器错误"),
    /* 1xxxx*/
    REDISUTILL_ERROR("10100","redis使用错误"),

    /* 2xxxx*/
    REGISTRY_MAIL_ERROE("20001","注册邮箱发送错误"),


    ;















    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    ErrorEnum(String code, String description){
        this.code = code;
        this.description = description;
    }
}

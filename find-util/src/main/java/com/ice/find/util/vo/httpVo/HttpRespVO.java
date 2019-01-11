/**
 * Copyright (C), 2018-2018,
 * FileName: HttpRespVO
 * Author:   Administrator
 * Date:     2018/12/5 21:08
 * Description: response entity of http
 */
package com.ice.find.util.vo.httpVo;

public class HttpRespVO {

    private String code;
    private String message;
    private Object data;

    public HttpRespVO(){
        this.code = "00000";
    }

    public HttpRespVO(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
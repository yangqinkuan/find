/**
 * Copyright (C), 2018-2018,
 * FileName: ByEmailVO
 * Author:   Administrator
 * Date:     2018/12/5 21:17
 * Description: email registry request entity
 */
package com.ice.find.vo.registry;

public class ByEmailVO {

    private String email;
    private String password;
    private String validCode;

    public ByEmailVO(){}

    public ByEmailVO(String email, String password, String validCode) {
        this.email = email;
        this.password = password;
        this.validCode = validCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
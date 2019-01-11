/**
 * FileName: LocalAuth
 * Author:   yangqinkuan
 * Date:     2019-1-11 10:12
 * Description: 本地验证 实体类
 */

package com.ice.find.sql.entity.user;

public class LocalAuth {
    private String  uuid;
    private Integer userId;
    private String phone;
    private String email;
    private String password;

    public LocalAuth() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}

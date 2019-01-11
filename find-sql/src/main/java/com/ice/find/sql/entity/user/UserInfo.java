/**
 * FileName: UserInfo
 * Author:   yangqinkuan
 * Date:     2019-1-11 10:39
 * Description: 用户信息类
 */

package com.ice.find.sql.entity.user;

import java.util.Date;

public class UserInfo {
    private Integer id;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private Date birth;

    public UserInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}

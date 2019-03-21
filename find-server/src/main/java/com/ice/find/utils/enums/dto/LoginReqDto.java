/**
 * FileName: LoginDto
 * Author:   yangqinkuan
 * Date:     2019-3-21 17:15
 * Description:
 */

package com.ice.find.utils.enums.dto;

import java.io.Serializable;

/**
 * 登录用实体
 */
public class LoginReqDto implements Serializable {
    private static final long serialVersionUID = 702016414931860178L;
    private String user_name;
    private String password;
    private String instanse;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getInstanse() {
        return instanse;
    }

    public void setInstanse(String instanse) {
        this.instanse = instanse;
    }
}

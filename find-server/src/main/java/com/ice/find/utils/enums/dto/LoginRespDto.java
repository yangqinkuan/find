/**
 * FileName: LoginRespDto
 * Author:   yangqinkuan
 * Date:     2019-3-21 17:35
 * Description:
 */

package com.ice.find.utils.enums.dto;

import java.io.Serializable;

public class LoginRespDto implements Serializable {

    private static final long serialVersionUID = -5211379757353654828L;
    private String token;
    private String chatIp;
    private String chatPort;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChatIp() {
        return chatIp;
    }

    public void setChatIp(String chatIp) {
        this.chatIp = chatIp;
    }

    public String getChatPort() {
        return chatPort;
    }

    public void setChatPort(String chatPort) {
        this.chatPort = chatPort;
    }
}

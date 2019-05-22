/**
 * FileName: ServerSession
 * Author:   yangqinkuan
 * Date:     2019-3-20 17:29
 * Description:
 */

package com.ice.find.server;

/**
 * 用于服务端的会话管理
 */
public class UserSession {
    private String userId;
    private String channelId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}

/**
 * FileName: ClientSession
 * Author:   yangqinkuan
 * Date:     2019-3-20 17:02
 * Description: 客户端会话管理
 */

package com.ice.find.client;

import io.netty.channel.Channel;

public class ClientSession {

    private String channelId;
    private Channel channel;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}

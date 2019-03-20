/**
 * FileName: ServerVariables
 * Author:   yangqinkuan
 * Date:     2019-3-20 18:03
 * Description:
 */

package com.ice.find.server;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于存储服务器用的变量
 */
public class ServerVariables {
    public static final String serverId = "00000";

    public static Map<String, Channel> channelMap =  new ConcurrentHashMap<>(1000);

    public static Map<String, UserSession> userSessionMap = new ConcurrentHashMap<>(1000);
}

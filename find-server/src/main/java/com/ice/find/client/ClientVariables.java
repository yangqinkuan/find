/**
 * FileName: ClientVariable
 * Author:   yangqinkuan
 * Date:     2019-3-20 17:49
 * Description:
 */

package com.ice.find.client;

import io.netty.channel.Channel;

/**
 * 对客户端的变量进行管理
 */
public class ClientVariables {

    public static final String clientId = "client000000";

    public static Channel channel;

    public static ClientSession clientSession;
}

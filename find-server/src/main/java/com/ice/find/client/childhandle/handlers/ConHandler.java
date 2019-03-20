/**
 * FileName: ConHandler
 * Author:   yangqinkuan
 * Date:     2019-3-20 18:31
 * Description:
 */

package com.ice.find.client.childhandle.handlers;

import com.ice.find.client.ClientVariables;
import com.ice.find.message.BusenessMessage;
import com.ice.find.message.MessageFactory;
import com.ice.find.utils.enums.EventType;
import io.netty.channel.Channel;

public class ConHandler {
    public void handleCon(BusenessMessage busenessMessage){
        String msg = MessageFactory.getBusenessMessage(EventType.CON_ACK,null, ClientVariables.clientId);
        Channel channel = ClientVariables.channel;
        System.out.println("收到服务器的响应");
        //channel.writeAndFlush(msg);
    }
}

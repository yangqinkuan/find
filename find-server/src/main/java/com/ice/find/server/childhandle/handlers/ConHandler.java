/**
 * FileName: ConHandler
 * Author:   yangqinkuan
 * Date:     2019-3-20 18:11
 * Description:
 */

package com.ice.find.server.childhandle.handlers;

import com.ice.find.message.BusenessMessage;
import com.ice.find.message.MessageFactory;
import com.ice.find.server.ServerVariables;
import com.ice.find.utils.enums.EventType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;


public class ConHandler {

    public void handleCon(BusenessMessage busenessMessage){
        ByteBuf msg = MessageFactory.getMessageByte(EventType.CON_ACK,null,ServerVariables.serverId);
        Channel channel = ServerVariables.channelMap.get(busenessMessage.getInstansId());
        channel.writeAndFlush(msg);
    }
}

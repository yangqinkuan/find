/**
 * FileName: ClientHandler
 * Author:   yangqinkuan
 * Date:     2019-1-22 15:54
 * Description:
 */

package com.ice.find.client;

import com.alibaba.fastjson.JSONObject;
import com.ice.find.client.childhandle.ChildFacade;
import com.ice.find.message.BusenessMessage;
import com.ice.find.message.Header;
import com.ice.find.message.MessageFactory;
import com.ice.find.message.NettyMessage;
import com.ice.find.server.ServerVariables;
import com.ice.find.utils.enums.EventType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private ChildFacade childFacade = new ChildFacade();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            BusenessMessage busenessMessage = JSONObject.parseObject((String) msg,BusenessMessage.class);
            logger.info("msgid{}--body{}",busenessMessage.getMessageId(), msg.toString());

            childFacade.childHandle(busenessMessage);
            //childFacade.childHandle(busenessMessage);
        } catch (Exception e) {
            logger.info("accept msg error ctxid{},exception{}",ctx.channel().id().asShortText(),e);
            ctx.close();
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        logger.info("通道开始注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("通道激活");
        ClientVariables.channel = ctx.channel();
        ctx.writeAndFlush(MessageFactory.getMessageByte(EventType.CON_REQ,null,ClientVariables.clientId));


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("失去连接.......");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("发送错误"+cause);
        cause.printStackTrace();
        ctx.close();
    }
    private NettyMessage getNettyMessage(){
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setLength(1234);
        header.setSessionID(123125124);
        String str = "这是一条消息";
        nettyMessage.setBody(str);
        return nettyMessage;
    }
}

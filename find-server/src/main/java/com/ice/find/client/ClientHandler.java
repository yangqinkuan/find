/**
 * FileName: ClientHandler
 * Author:   yangqinkuan
 * Date:     2019-1-22 15:54
 * Description:
 */

package com.ice.find.client;

import com.ice.find.message.Header;
import com.ice.find.message.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        logger.info("通道开始注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("通道激活");
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setLength(1234);
        nettyMessage.setHeader(header);
        nettyMessage.setBody("aaaaaaaaaa");
        System.out.println(nettyMessage.toString());
        ctx.writeAndFlush(nettyMessage);
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
        String str = "这是一条消息";
        nettyMessage.setBody(str);
        return nettyMessage;
    }
}

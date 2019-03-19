/**
 * FileName: BusinessHandler
 * Author:   yangqinkuan
 * Date:     2019-1-22 14:32
 * Description:
 */

package com.ice.find.server;




import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class BusinessHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    int count = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("开始读取信息");
        if(msg instanceof String){
            System.out.println(msg.toString());
        }
        /*        NettyMessage nettyMessage = (NettyMessage)msg;
        System.out.println(nettyMessage.toString());
        String res = new String("心跳响应");
        nettyMessage.setBody(res);*/
        //ctx.writeAndFlush("bbbbbbbbbbbbbb");


    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("通道开始注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("通道激活");
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
}

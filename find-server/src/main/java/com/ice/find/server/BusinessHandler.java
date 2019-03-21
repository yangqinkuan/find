/**
 * FileName: BusinessHandler
 * Author:   yangqinkuan
 * Date:     2019-1-22 14:32
 * Description:
 */

package com.ice.find.server;




import com.alibaba.fastjson.JSONObject;
import com.ice.find.message.BusenessMessage;
import com.ice.find.server.childhandle.ChildFacade;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class BusinessHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ChildFacade childFacade = new ChildFacade();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            BusenessMessage busenessMessage = JSONObject.parseObject((String) msg,BusenessMessage.class);
            logger.info("msgid{}--body{}",busenessMessage.getMessageId(), msg.toString());
            childFacade.childHandle(busenessMessage,ctx.channel().id().asShortText());
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
        ServerVariables.channelMap.put(ctx.channel().id().asShortText(),ctx.channel());
        logger.info("通道激活");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ServerVariables.channelMap.remove(ctx.channel().id());
        super.channelInactive(ctx);
        logger.info("失去连接.......,移除channel");
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

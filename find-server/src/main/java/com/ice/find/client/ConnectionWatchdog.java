/**
 * FileName: ConnectionWatchdog
 * Author:   yangqinkuan
 * Date:     2019-3-18 18:45
 * Description:
 */

package com.ice.find.client;

import com.ice.find.HeartTest.ChannelHandlerHolder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * 重连检测
 */
@ChannelHandler.Sharable
public abstract class ConnectionWatchdog extends ChannelInboundHandlerAdapter implements TimerTask,ChannelHandlerHolder{

    private Bootstrap bootstrap;
    private Timer timer;
    private int port;
    private String host;
    private volatile boolean reconnect = true;
    private int attempts;

    public ConnectionWatchdog(Bootstrap bootstrap, Timer timer, int port, String host, boolean reconnect) {
        this.bootstrap = bootstrap;
        this.timer = timer;
        this.port = port;
        this.host = host;
        this.reconnect = reconnect;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("当前链路已经激活了，重连尝试次数重新置为0");
//        attempts = 0;
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("链接关闭");
        if(reconnect){
            System.out.println("链接关闭，将进行重连");
            timer.newTimeout(this, 1, TimeUnit.SECONDS);
        }
        ctx.fireChannelInactive();
    }



    @Override
    public void run(Timeout timeout) throws Exception {
        ChannelFuture future;
        //bootstrap已经初始化好了,只需要将handler填入就可以了
        synchronized (bootstrap){
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(handlers());
                }
            });
            future = bootstrap.connect(host,port);
        }
        //future对象
        future.addListener((ChannelFutureListener) channelFuture -> {
            boolean succeed = future.isSuccess();

            //如果重连失败,调用ChannelInactive方法,再次除法重连事件,一直尝试12次，如果失败则不再尝试重连
            if(!succeed){
                System.out.println("重连失败");
                channelFuture.channel().pipeline().fireChannelInactive();
            }else {
                System.out.println("重连成功");
            }
        });
    }

}

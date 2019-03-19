/**
 * FileName: HeartBeatsClient
 * Author:   yangqinkuan
 * Date:     2019-3-18 17:29
 * Description:
 */

package com.ice.find.HeartTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

public class HeartBeatsClient {

    protected final HashedWheelTimer timer = new HashedWheelTimer();

    private Bootstrap boot;

    private final ConnectorIdleStateTrigger idleStateTrigger = new ConnectorIdleStateTrigger();

    public void connect(int port,String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        boot = new Bootstrap();
        boot.group(group).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO));

        final ConnectionWatchdog watchdog = new ConnectionWatchdog(boot,timer,port,host,true){
            public ChannelHandler[] handlers(){
                return new ChannelHandler[]{
                        this,
                        new IdleStateHandler(0,4,0, TimeUnit.SECONDS),
                        idleStateTrigger,
                        new StringDecoder(),
                        new StringEncoder(),
                        new HeartBeatClientHandler()
                };
            }
        };
        ChannelFuture future;
        //进行连接
        try{
            synchronized (boot){
                boot.handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(watchdog.handlers());
                    }
                });
                future = boot.connect(host,port);
            }
            future.sync();
        }catch(Exception e){
            throw new Exception("connects to fails",e);
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args !=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){

            }
        }
        new HeartBeatsClient().connect(port,"127.0.0.1");
    }


}

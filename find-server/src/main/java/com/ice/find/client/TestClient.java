/**
 * FileName: TestClient
 * Author:   yangqinkuan
 * Date:     2019-1-22 15:48
 * Description: 测试用客户端
 */

package com.ice.find.client;

import com.ice.find.HeartTest.HeartBeatClientHandler;
import com.ice.find.message.Body;
import com.ice.find.message.MessageFactory;
import com.ice.find.utils.enums.EventType;
import com.ice.find.utils.enums.dto.LoginReqDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

public class TestClient {

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
                        new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("_$".getBytes())),
                        new StringDecoder(),
                        new StringEncoder(),
                        new ClientHandler()
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
        int port = 9500;
        if(args != null && args.length>0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                //采取默认值
            }
        }
        new TestClient().connect(port,"127.0.0.1");

        System.out.println("已经连接，开始登陆");
        while(ClientVariables.channel==null){
            System.out.println("还没连接上,请检查网络");
            Thread.sleep(20);
        }
        Body body = new Body();
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setUser_name("yqk");
        loginReqDto.setPassword("123");
        body.setData(loginReqDto);
        ByteBuf msg = MessageFactory.getMessageByte(EventType.LOGIN_REQ,body,ClientVariables.clientId);
        ClientVariables.channel.writeAndFlush(msg);






    }
}

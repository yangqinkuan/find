/**
 * FileName: TestClient
 * Author:   yangqinkuan
 * Date:     2019-1-22 15:48
 * Description: 测试用客户端
 */

package com.ice.find.client;

import com.ice.find.Factory.MarshallingCodeCFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;

public class TestClient {
    @Autowired
    private AdminClient adminClient;

    public void connect(int port,String host) throws Exception{
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY,true);
            b.handler(new ChildChannelHandler());

            ChannelFuture f = b.connect(host,port).sync();//发起异步连接操作
            f.channel().closeFuture().sync();//等待客户端链路关闭
        } finally {
            group.shutdownGracefully();
        }
    }
    //子Handler
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //设置Marshalling的编码和解码
            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
            socketChannel.pipeline().addLast(new ClientHandler());
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
    }
}

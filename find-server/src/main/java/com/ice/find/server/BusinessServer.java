/**
 * FileName: BusinessServer
 * Author:   yangqinkuan
 * Date:     2019-1-22 12:31
 * Description: 业务服务器
 */

package com.ice.find.server;

import com.ice.find.factory.MarshallingCodeCFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class BusinessServer {

    public void bind(int port) throws Exception{
        //1、第一个线程组用于接受client端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2、第二个线程组用于实际的业务处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b;
        try {
            b = new ServerBootstrap();
            b.group(bossGroup,workerGroup);//绑定两个线程池
            b.channel(NioServerSocketChannel.class);//指定NIO的模式，如果是客户端就是NioSocketChannel
            b.option(ChannelOption.SO_BACKLOG, 1024);//TCP的 accept队列大小
            //b.option(ChannelOption.SO_SNDBUF,32*1024);//设置发送缓冲区的大小
            //b.option(ChannelOption.SO_RCVBUF,32*1024);//设置接受缓冲区的大小
            //b.option(ChannelOption.SO_KEEPALIVE,true);//保持连续

            //加入Handler
            b.childHandler(new ChildChannelHandler());



            ChannelFuture f = b.bind(port).sync();//绑定端口
            f.channel().closeFuture().sync();//等待服务器监听端口关闭
        } finally {
            //优雅推出,释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
    //子Handler
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //设置Marshalling的编码和解码
            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
            socketChannel.pipeline().addLast(new BusinessHandler());
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
        new BusinessServer().bind(port);
    }
}

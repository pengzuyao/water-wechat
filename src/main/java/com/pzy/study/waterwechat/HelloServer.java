package com.pzy.study.waterwechat;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-09-01
 */
public class HelloServer {

    public static void main(String[] args) {

        //负责处理连接线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责处理任务线程组
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try{

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup ,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HelloServerInitialzer());

            //启动server，设置启动方式为同步
            ChannelFuture channelFuture = bootstrap.bind(8080).sync();
            //监听关闭的channel,设置为同步方式
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}

package com.pzy.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-09-01
 */
@Slf4j
@Component
public class WSServer {

    private static  class Inner{
        private static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance(){
        return Inner.instance;
    }

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workGroup;
    private ServerBootstrap bootstrap;
    private  ChannelFuture future;

    private WSServer(){
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup ,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    public void start(){
        this.future = bootstrap.bind(8088);
        log.info("netty启动完毕……");
    }

}

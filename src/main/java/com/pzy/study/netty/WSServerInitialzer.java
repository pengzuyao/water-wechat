package com.pzy.study.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-09-01
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        //websocket 基于http协议，所以要有http协议编译解码器
        pipeline.addLast(new HttpServerCodec());
        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        /**
         *  webSocket 服务器处理的协议，用于指定给客户端链接访问的路由：/ws
         *  本handler会帮你处理一些繁重的复杂的事
         *  会帮你处理握手动作：handshaking(close ,ping ,pong) ping + pong = 心跳
         *  对于webSocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //添加自定义的hangdler
        pipeline.addLast(new ChatHandler());


    }
}

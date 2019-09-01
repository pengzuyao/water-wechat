package com.pzy.study.waterwechat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-09-01
 */
public class HelloServerInitialzer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {


        // 通过SocketChannel去获得对应的管道
        ChannelPipeline pipeline = channel.pipeline();

        // 通过管道，添加handler
        // HttpServerCodec是由netty自己提供的助手类，可以理解为拦截器
        // 当请求到服务端，我们需要做解码，响应到客户端做编码
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        // 添加自定义的助手类，返回 "hello netty~"
        pipeline.addLast("customHandler", new CustomHandler());
    }
}

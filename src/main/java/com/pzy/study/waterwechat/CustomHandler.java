package com.pzy.study.waterwechat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-09-01
 */
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //获取channel
        Channel channel = ctx.channel();
        if (msg instanceof HttpRequest){
            //显示客户端的远程地址
            System.out.println(channel.remoteAddress());
            //定义发送的数据消息
            ByteBuf context = Unpooled.copiedBuffer("hello netty!" , CharsetUtil.UTF_8);

            //构建一个http response
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1 ,
                    HttpResponseStatus.OK ,
                    context);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE , "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH ,context.readableBytes());

            //把响应刷到客户端
            ctx.writeAndFlush(response);

        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("注册……channel");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("移除……channel");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("活跃……channel");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("不活跃……channel");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读取完毕……channel");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("用户事件触发……");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel可写更改");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("捕获到异常");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类添加");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("助手类移除");
        super.handlerRemoved(ctx);
    }
}

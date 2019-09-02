package com.pzy.study.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * Destription: 处理消息的handler
 * TextWebSocketFrame :在netty中，是用于为websocket专门处理文本的对象 ，frame 是消息的载体
 * Author: pengzuyao
 * Time: 2019-09-01
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        //获取客户端传过来的消息
        String context = msg.text();
        for (Channel channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame("[服务器在]" + LocalDateTime.now() + "接受到消息,消息为:" + context));
        }
    }


    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channel,并且放到channelGroup中去进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       clients.add(ctx.channel()) ;
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发hangdlerRemoved ,ChannelGroup会自动移除客户端的channel
        //clients.remove(ctx.channel());
        System.out.println("客户端断开，channel对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channel对应的短id为："+ ctx.channel().id().asShortText());
    }
}

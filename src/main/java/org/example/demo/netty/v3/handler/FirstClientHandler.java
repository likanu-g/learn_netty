package org.example.demo.netty.v3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + " ：客户端写入数据");
        //获取数据
        ByteBuf byteBuf = getByteBuf(ctx);
        //写入数据
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + " : 客户端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //创建ByteBuf对象
        ByteBuf byteBuf = ctx.alloc().buffer();
        //指定输入的数据
        byte[] bytes = "你好，ε=(´ο｀*)))".getBytes(StandardCharsets.UTF_8);
        //将指定的数据写入到ByteBuf中
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}

package org.example.demo.netty.v3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import org.example.demo.netty.v3.handler.FirstClientHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        //添加数据的处理器FirstClientHandler
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                })
                .connect("localhost", 8000).addListener(future -> {
           if(future.isSuccess()) {
               System.out.println("连接成功!");
           }else {
               System.out.println("连接失败!");
           }
        });

    }
}

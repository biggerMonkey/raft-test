package com.person.raft.admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.person.raft.admin.netty.SimpleChatClientInitializer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Hello world!
 *
 */
public class RaftAdminStartup {
    public static void main(String[] args) throws Exception {
        new RaftAdminStartup("127.0.0.1", 9999).run();
    }

    private final String host;
    private final int port;

    public RaftAdminStartup(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer());
            Channel channel = bootstrap.connect(host, port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Thread.sleep(2000);
            while (true) {
                // System.out.println("输入：" + in.readLine());
                ChannelFuture future = channel.writeAndFlush(in.readLine() + "\r\n");
                // future.wait();
                // channel.flush();
                // channel.writeAndFlush("hello world\r\n");
                // channel.flush();
                // channel.wait(1000);
                System.out.println("发送成功！");
            }
        } catch (

        Exception e)

        {
            e.printStackTrace();
        } finally

        {
            group.shutdownGracefully();
        }

    }
}

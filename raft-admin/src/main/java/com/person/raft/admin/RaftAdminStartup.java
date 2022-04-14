package com.person.raft.admin;

import com.person.raft.admin.netty.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class RaftAdminStartup implements Runnable{


    public static void main(String[] args) throws Exception {
        RaftAdminStartup raftAdminStartup = new RaftAdminStartup("localhost", 9999);
//        raftAdminStartup.run();
        new Thread(raftAdminStartup).start();
        raftAdminStartup.send();
    }

    private final String host;
    private final int port;

    private ClientHandler clientHandler = new ClientHandler();

    public RaftAdminStartup(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println("输入：" + input);
            clientHandler.sendMsg(input);
        }
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap =
                    new Bootstrap()
                            .group(group)
                            .channel(NioSocketChannel.class)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ChannelPipeline pipeline = ch.pipeline();

                                    pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Unpooled.copiedBuffer(System.getProperty("line.separator").getBytes())));
                                    pipeline.addLast("decoder", new StringDecoder());
                                    pipeline.addLast("encoder", new StringEncoder());
                                    pipeline.addLast("handler", clientHandler);
                                }
                            });
            ChannelFuture f = bootstrap.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
}

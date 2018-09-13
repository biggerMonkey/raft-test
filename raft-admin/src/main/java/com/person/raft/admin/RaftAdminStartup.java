package com.person.raft.admin;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

import com.person.raft.admin.netty.SimpleChatClientInitializer;

/**
 * Hello world!
 *
 */
public class RaftAdminStartup {
    public static void main(String[] args) throws Exception {

        new RaftAdminStartup("127.0.0.1", 9999).sendSync();
    }

    private final String host;
    private final int port;

    public RaftAdminStartup(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendSync() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap =
                    new Bootstrap().group(group).channel(NioSocketChannel.class)
                            .handler(new SimpleChatClientInitializer());
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            SocketChannel channel = SocketChannel.open();
            channel.socket().connect(socketAddress, 1000 * 5);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (int i = 0; i < 5; i++) {
                // System.out.println("输入：" + in.readLine());
                String msg = "test" + LocalDateTime.now().toString() + "\r\n";
                channel.socket().setSoTimeout(1000);
                int len = channel.write(ByteBuffer.wrap(msg.getBytes()));
                System.out.println("len=" + len);
                Thread.sleep(1000);
                ByteBuffer byteBufferSize = ByteBuffer.allocate(64);
                int length = channel.read(byteBufferSize);
                System.out.println("len2=" + length);
                System.out.println("结果：" + new String(byteBufferSize.array()));
                System.out.println("发送成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("哦豁！服务器凉了！");
        } finally {
            group.shutdownGracefully();
        }

    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap =
                    new Bootstrap().group(group).channel(NioSocketChannel.class)
                            .handler(new SimpleChatClientInitializer());
            Channel channel = bootstrap.connect(host, port).channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                // System.out.println("输入：" + in.readLine());
                ChannelFuture future = channel.writeAndFlush(in.readLine() + "\r\n");
                future.wait(2 * 1000);
                System.out.println(future.get().toString());
                // future.wait();
                // channel.flush();
                // channel.writeAndFlush("hello world\r\n");
                // channel.flush();
                // channel.wait(1000);
                System.out.println("发送成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("哟嚯！服务器凉了！");
        } finally {
            group.shutdownGracefully();
        }

    }
}

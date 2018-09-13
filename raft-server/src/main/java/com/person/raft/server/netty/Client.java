package com.person.raft.server.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author huangwenjun
 * @Date 2018年8月31日
 */
public class Client extends Thread {

    private String host;
    private int port;

    public Client(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            SocketChannel socketChannel = SocketChannel.open(socketAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            socketChannel.socket().setSoTimeout(1000);
            int len = socketChannel.write(ByteBuffer.wrap("test\r\n".getBytes()));
            System.out.println("len=" + len);
            Thread.sleep(1000);
            ByteBuffer byteBufferSize = ByteBuffer.allocate(64);
            int length = socketChannel.read(byteBufferSize);
            System.out.println("len2=" + length);
            System.out.println("结果：" + new String(byteBufferSize.array()));
            System.out.println("发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}

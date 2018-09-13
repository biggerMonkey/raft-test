package com.person.raft.server.netty;

import java.net.InetAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author huangwenjun
 * @Date 2018年7月12日
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client msg:" + msg);
        // String clientIdToLong = ctx.channel().id().asLongText();
        // System.out.println("client long id:" + clientIdToLong);
        // String clientIdToShort = ctx.channel().id().asShortText();
        // System.out.println("client short id:" + clientIdToShort);

    }

    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    // System.out.println("channelRead client msg:" + msg);
    // }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.channel().writeAndFlush(
                "客户端的发送的消息:Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("\nChannel is disconnected");
        super.channelInactive(ctx);
    }
}

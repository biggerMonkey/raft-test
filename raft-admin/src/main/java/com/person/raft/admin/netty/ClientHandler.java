package com.person.raft.admin.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * @author huangwenjun
 * @Date 2018年7月12日
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    private ChannelHandlerContext ctx;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client msg:" + msg);
        String clientIdToLong = ctx.channel().id().asLongText();
        System.out.println("client long id:" + clientIdToLong);
        String clientIdToShort = ctx.channel().id().asShortText();
        System.out.println("client short id:" + clientIdToShort);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.channel().writeAndFlush(
                "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
        this.ctx = ctx;
    }

    public void sendMsg(String msg) {
        this.ctx.channel().writeAndFlush(msg+"\n");
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

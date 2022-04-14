package com.person.raft.server.netty;

import com.person.raft.server.cache.ChnnelCache;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

/**
 * @author huangwenjun
 * @Date 2018年7月9日
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server msg:" + msg);
        String clientIdToLong = ctx.channel().id().asLongText();
        System.out.println("server long id:" + clientIdToLong);
        String clientIdToShort = ctx.channel().id().asShortText();
        System.out.println("server short id:" + clientIdToShort);
        ctx.channel().writeAndFlush("Yoru msg is:" + msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server msg:" + msg);
        String clientIdToLong = ctx.channel().id().asLongText();
        System.out.println("server long id:" + clientIdToLong);
        String clientIdToShort = ctx.channel().id().asShortText();
        System.out.println("server short id:" + clientIdToShort);
        ctx.channel().writeAndFlush("Yoru msg is:" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.channel().writeAndFlush(
                "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

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

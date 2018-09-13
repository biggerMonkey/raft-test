package com.person.raft.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * @author huangwenjun
 * @Date 2018年7月9日
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    // private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接收到消息：msg:" + msg);
        Thread.sleep(50000);
        // log.info("server msg:" + msg);
        // String clientIdToLong = ctx.channel().id().asLongText();
        // log.info("server long id:" + clientIdToLong);
        // String clientIdToShort = ctx.channel().id().asShortText();
        // log.info("server short id:" + clientIdToShort);
        ctx.writeAndFlush("Yoru msg is:" + msg + "\n");

    }

    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    // System.out.println("channelRead msg:" + msg);
    // ctx.writeAndFlush("Yoru msg is:" + msg);
    //
    // }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.channel().writeAndFlush(
                "服务端的发送的消息:Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

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

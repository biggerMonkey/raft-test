package com.person.raft.server.cache;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author huangwenjun
 * @Date 2022/4/14
 * @Description
 **/
public class ChnnelCache {
    public static final Map<String, ChannelHandlerContext> channelMap = new ConcurrentHashMap<>();
}

package com.person.raft.server;

import java.util.Properties;

import com.person.raft.server.config.ServerConfig;
import com.person.raft.server.netty.Server;


/**
 * Hello world!
 *
 */
public class RaftServerStartup {
    private static Properties properties = null;
    private static ServerConfig serverConfig = null;

    public static void main(String[] args) throws Exception {
        args = new String[2];
        args[0] = "-c";
        args[1] = "E:/java/code/raft-test/server1.properties";
        for (String cmd : args) {
            System.out.println("cmd:" + cmd);
        }
        serverConfig = new ServerConfig();
        CmdHandle.cmdHandler(properties, serverConfig, args);
        System.out.println("Hello world!");
        new Server(serverConfig).start();
        System.out.println("over");
    }
}

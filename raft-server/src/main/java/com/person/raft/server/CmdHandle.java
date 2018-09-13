package com.person.raft.server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.person.raft.server.config.ServerConfig;
import com.person.raft.server.utils.PropertiesUtils;


/**
 * @author huangwenjun
 * @Date 2018年7月9日
 */
public class CmdHandle {

    public static void cmdHandler(Properties properties, ServerConfig serverConfig, String[] args) {

        if (args == null || args.length == 0) {
            return;
        }
        if (args[0].indexOf("c") > 0) {
            String file = args[1];
            if (file != null) {
                try {
                    InputStream in = new BufferedInputStream(new FileInputStream(file));
                    properties = new Properties();
                    properties.load(in);
                    PropertiesUtils.properties2Object(properties, serverConfig);
                    System.out.printf("load config properties file OK, " + file + "%n");
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

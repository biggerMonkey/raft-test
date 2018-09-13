package com.person.raft.common;

import com.person.raft.common.constant.ServerStatusEnum;
import com.person.raft.common.constant.ServerTypeEnum;

/**
 * @author huangwenjun
 * @Date 2018年8月31日
 */
public class ServerNodeInfo {
    private String ip;
    private String port;
    private ServerStatusEnum serverStatus;
    private ServerTypeEnum serverType;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public ServerStatusEnum getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatusEnum serverStatus) {
        this.serverStatus = serverStatus;
    }

    public ServerTypeEnum getServerType() {
        return serverType;
    }

    public void setServerType(ServerTypeEnum serverType) {
        this.serverType = serverType;
    }
}

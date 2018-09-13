package com.person.raft.server.config;

/**
 * @author huangwenjun
 * @Date 2018年8月31日
 */
public class ServerConfig {
    private int listenPort = 9999;// 服务器默认监听端口，如果部署在一台机器，需要指定端口
    private int masterToSlavelTimeOut = 10;// master向slave发送心跳，超过10s没有响应，表示slave已经凉了
    private String otherServerAddr;// 其他服务地址，只需要指定其中一个即可加入集群

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public int getMasterToSlavelTimeOut() {
        return masterToSlavelTimeOut;
    }

    public void setMasterToSlavelTimeOut(int masterToSlavelTimeOut) {
        this.masterToSlavelTimeOut = masterToSlavelTimeOut;
    }

    public String getOtherServerAddr() {
        return otherServerAddr;
    }

    public void setOtherServerAddr(String otherServerAddr) {
        this.otherServerAddr = otherServerAddr;
    }

}

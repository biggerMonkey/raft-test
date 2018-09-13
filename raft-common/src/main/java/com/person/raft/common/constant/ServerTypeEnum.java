package com.person.raft.common.constant;

/**
 * @author huangwenjun
 * @Date 2018年8月31日
 */
public enum ServerTypeEnum {

    LEADER("leader"), FOLLOWER("follower"), CANDIDATE("candidate");
    private String serverType;

    private ServerTypeEnum(String serverType) {
        this.serverType = serverType;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }


}

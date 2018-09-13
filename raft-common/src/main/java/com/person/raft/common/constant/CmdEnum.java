package com.person.raft.common.constant;

/**
 * @author huangwenjun
 * @Date 2018年8月31日
 */
public enum CmdEnum {

    BEAT("beat");

    private String cmd;

    private CmdEnum(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }

}

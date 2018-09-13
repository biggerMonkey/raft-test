package com.person.raft.common.constant;

/**
 * @author huangwenjun
 * @Date 2018年8月31日
 */
public enum ServerStatusEnum {

    ALIVE(1), DEATH(0), NOT_EXIST(2);

    private ServerStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

}

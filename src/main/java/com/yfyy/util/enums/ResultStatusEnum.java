package com.yfyy.util.enums;

/**
 * 返回客户端状态
 */
public enum ResultStatusEnum {

    /**
     * 失败
     */
    FAILURE(0),

    /**
     * 成功
     */
    SUCCESS(1),

    /**
     * 没有权限
     */
    NO_AUTH(3);

    private final int status;

    ResultStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

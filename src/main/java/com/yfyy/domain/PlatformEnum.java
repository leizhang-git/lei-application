package com.yfyy.domain;

/**
 * @Author: zhang lei
 * @DateTime: 2022/5/12 16:56
 */
public enum PlatformEnum {

    /**
     * JWT管理
     */
    JWT("JWT"),

    /**
     * 无权限管理
     */
    NORMAL("NORMAL");

    private final String value;

    PlatformEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

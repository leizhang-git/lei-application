package com.yfyy.domain;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/13 10:39
 */
public enum JwtEnum {

    NORMAL(1),

    KEY(0),

    TOKEN_FAIL(0),

    TOKEN_NOT_EXIST(0);

    JwtEnum(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;
}

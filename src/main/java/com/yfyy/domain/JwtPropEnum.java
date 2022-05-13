package com.yfyy.domain;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/13 10:50
 */
public enum JwtPropEnum {

    /**
     * 默认角色
     */
    DEFAULT_ROLE("admin"),

    /**
     * 默认userId
     */
    DEFAULT_USER_ID("default-user-id"),

    /**
     * 默认UserName
     */
    DEFAULT_USERNAME("default-username"),

    /**
     * 默认组织
     */
    DEFAULT_ORGANIZATION("default-organization");

    private String code;

    public String getCode() {
        return code;
    }

    JwtPropEnum(String code){
        this.code = code;
    }

    @Override
    public String toString() {
        return getCode();
    }
}

package com.yfyy.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: zhang lei
 * @Date: 2022/4/10 00:23
 */
@Data
@Builder
public class UserDTO {

    /**
     * 主键
     * @GeneratorValue JPA通用策略生成器
     * @GenericGenerator 自定义主键生成策略
     */
    private String id;

    /**
     * UserId
     * @Pattern 正则
     */
    private String userId;

    /**
     * password
     * @JsonIgnore 实体类向前台返回数据时用来忽略不想传递给前台的属性或接口
     */
    private String password;

    /**
     * 初始姓名
     */
    private String firstName;

    /**
     * 对外网名
     */
    private String lastName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String headSculpture;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 所属组织
     */
    private String organization;


    /**
     * 角色信息
     */
    private String roles;
}

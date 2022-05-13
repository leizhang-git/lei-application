package com.yfyy.domain;

import lombok.Data;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 17:20
 */
@Data
public class JwtUser extends User{

    private String role;
}

package com.yfyy.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 17:30
 */
@Data
public class JwtKeyVO implements Serializable {
    private String key;

    private Long expire;
}

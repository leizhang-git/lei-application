package com.yfyy.anno;

import java.lang.annotation.*;

/**
 * @Author: zhang lei
 * @Date: 2022/4/9 22:18
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)     //允许通过反射获取注解信息
public @interface CheckLogin {
    String value() default "normal";
}

package com.yfyy.config.condition;

import com.yfyy.domain.PlatformEnum;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @Author: zhang lei
 * @DateTime: 2022/5/12 16:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnSystemContextCondition.class)
public @interface ConditionOnSystemContext {

    /**
     * 系统属性值
     * @return
     */
    PlatformEnum[] value() default {PlatformEnum.JWT};
}

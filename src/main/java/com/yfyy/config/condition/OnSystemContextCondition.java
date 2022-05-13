package com.yfyy.config.condition;


import com.yfyy.domain.PlatformEnum;
import com.yfyy.util.SettingUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author: zhang lei
 * @DateTime: 2022/5/12 16:53
 */
public class OnSystemContextCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionOnSystemContext.class.getName());
        PlatformEnum[] value = (PlatformEnum[]) attributes.get("value");
        HashSet<PlatformEnum> platformEnums = new HashSet<>(Arrays.asList(value));
        PlatformEnum plat = SettingUtil.getBuildPlatform();
        return platformEnums.contains(plat);
    }
}

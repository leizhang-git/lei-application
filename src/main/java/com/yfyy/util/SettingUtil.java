package com.yfyy.util;

import cn.hutool.core.util.StrUtil;
import com.yfyy.domain.PlatformEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 16:58
 */
@Component
public class SettingUtil {

    private static final Logger log = LoggerFactory.getLogger(SettingUtil.class);

    private static Properties properties;

    private static PlatformEnum[] auths = new PlatformEnum[]{PlatformEnum.JWT};

    /**
     * 构建平台
     */
    private static String PLATFORM_KEY = "build.platform";

    public static String get(String key) {
        String value = System.getProperty(key);
        if(StringUtils.isNotEmpty(value)){
            return value;
        }
        return properties.getProperty(key, "");
    }

    public static PlatformEnum getBuildPlatform() {
        String s = get(PLATFORM_KEY);
        if (StrUtil.isBlank(s)) {
            log.info("配置文件中没有配置编译平台，默认使用带YHT的平台");
            return PlatformEnum.JWT;
        }
        return PlatformEnum.valueOf(s);
    }
}

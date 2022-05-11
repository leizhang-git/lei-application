package com.yfyy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

/**
 * @Author: zhang lei
 * @DateTime: 2022/5/11 16:44
 * @EnableCaching
 * @DependsOn  该注解可以定义在类和方法上，意思是我这个组件要依赖于另一个组件，也就是说被依赖的组件会比该组件先注册到IOC容器中
 */
@Configuration
@EnableCaching
@DependsOn("leiSpringContextHolder")
public class CacheConfiguration {

    private final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

    private final Environment env;

    public CacheConfiguration(Environment env) {
        this.env = env;
    }

    @Value("${lei.boot.redis.refs.defaultDef:iei-redis}")
    private String leiRedisRefsDefaultDef;
}

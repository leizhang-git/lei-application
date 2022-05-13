package com.yfyy.security;

import com.yfyy.config.condition.ConditionOnSystemContext;
import com.yfyy.domain.PlatformEnum;
import com.yfyy.security.jwt.JWTFilter;
import com.yfyy.security.jwt.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhang lei
 * @DateTime: 2022/5/12 16:51
 */
@Slf4j
@Configuration
@ConditionOnSystemContext({PlatformEnum.JWT})
public class SecurityConfiguration {

    private TokenProvider tokenProvider;

    public SecurityConfiguration(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
        log.info("ExclusiveSecurityConfiguration===========: 实例化 JWT");
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter(tokenProvider);
    }

}

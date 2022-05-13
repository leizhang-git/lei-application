package com.yfyy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zhanglei
 * @DateTime: 2022/5/12 17:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ApplicationProperties.Jwt jwt = new ApplicationProperties.Jwt();



    public static class Jwt {
        private Long expireTime;
        private String debugToken;
        private String debugOrganization;
        private boolean debug = false;
        private List<String> ignoreUrls;

        public List<String> getIgnoreUrls() {
            return ignoreUrls;
        }

        public void setIgnoreUrls(List<String> ignoreUrls) {
            this.ignoreUrls = ignoreUrls;
        }


        public Jwt() {
        }

        public Long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Long expireTime) {
            this.expireTime = expireTime;
        }

        public String getDebugToken() {
            return debugToken;
        }

        public void setDebugToken(String debugToken) {
            this.debugToken = debugToken;
        }

        public boolean isDebug() {
            return debug;
        }

        public void setDebug(boolean debug) {
            this.debug = debug;
        }

        public String getDebugOrganization() {
            return debugOrganization;
        }

        public void setDebugOrganization(String debugOrganization) {
            this.debugOrganization = debugOrganization;
        }
    }
}

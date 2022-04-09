package com.yfyy;

import cn.hutool.core.util.StrUtil;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@SpringBootConfiguration
@ComponentScan(basePackages = {"com.yfyy.*","com.yfyy.repository","com.yfyy.service"})
@EnableConfigurationProperties({LiquibaseProperties.class, JpaProperties.class})
@EnableEncryptableProperties
@SpringBootApplication
public class LeiApplication {

    private static final Logger log = LoggerFactory.getLogger(LeiApplication.class);


    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enable", "false");
        System.setProperty("server.port", "8088");
        SpringApplication springApplication = new SpringApplication(LeiApplication.class);
        ConfigurableApplicationContext ctx = springApplication.run(args);
        Environment env = ctx.getEnvironment();
        log.info("Bean总数量为{}", ctx.getEnvironment());
        String[] restBeans = ctx.getBeanNamesForAnnotation(RestController.class);
        String[] repoBeans = ctx.getBeanNamesForAnnotation(Repository.class);
        String[] serviceBeans = ctx.getBeanNamesForAnnotation(Service.class);
        int i = 0;
        for (String str : restBeans) {
            log.debug("rest: {},beanName:{}", ++i, str);
        }
        i = 0;
        for (String str : repoBeans) {
            log.debug("repo: {},beanName:{}", ++i, str);
        }
        i = 0;
        for (String str : serviceBeans) {
            log.debug("service: {},beanName:{}", ++i, str);
        }
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment environment) {
        String protocol = "http";
        if(environment.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        if(StrUtil.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "127.0.0.1";
        log.info("\n===========================================================\n\t" +
                        "Application '{}' is running! Access Urls is:\n\t" +
                        "local: \t\t{}://localhost:{}{}\n\t" +
                        "External: \t{}://{}:{}{}\n\t" +
                        "Profile(s): \t{}\n==============================================",
                environment.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                environment.getActiveProfiles());
    }
}

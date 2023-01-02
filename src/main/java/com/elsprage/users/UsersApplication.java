package com.elsprage.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class UsersApplication {

    public static void main(String... args) {
        final SpringApplicationBuilder builder = new SpringApplicationBuilder(UsersApplication.class);
        final ConfigurableApplicationContext context = builder.run(args);
        log.info(getStartupInfo(context));
    }

    static String getStartupInfo(final ConfigurableApplicationContext context) {
        final Environment env = context.getEnvironment();
        final String port = env.getProperty("server.port");
        final List<String> profile = Arrays.asList(env.getActiveProfiles());
        return "Started Spring Boot application: UsersApplication on port: " + port + " with profile " + profile;
    }
}

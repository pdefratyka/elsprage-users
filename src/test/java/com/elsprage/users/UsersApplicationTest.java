package com.elsprage.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class UsersApplicationTest {

    @Test
    void shouldGetStartUpInfo() {
        final ConfigurableApplicationContext ctx = Mockito.mock(ConfigurableApplicationContext.class);
        final ConfigurableEnvironment env = Mockito.mock(ConfigurableEnvironment.class);
        Mockito.when(env.getProperty("server.port")).thenReturn("8080");
        Mockito.when(env.getActiveProfiles()).thenReturn(new String[]{"local"});
        Mockito.when(ctx.getEnvironment()).thenReturn(env);

        Assertions.assertEquals("Started Spring Boot application: UsersApplication " +
                        "on port: 8080 with profile [local]",
                UsersApplication.getStartupInfo(ctx));
    }
}
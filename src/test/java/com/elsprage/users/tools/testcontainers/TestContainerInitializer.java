package com.elsprage.users.tools.testcontainers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestContainerInitializer {

    public static void start(ContainerInitializer<?>... containerInitializers) {
        final Instant start = Instant.now();
        log.info("Initializing container.");
        Arrays.stream(containerInitializers).parallel().forEach(ContainerInitializer::startAndGet);
        log.info("TestContainers started in {} seconds", Duration.between(start, Instant.now()).toSeconds());
    }
}

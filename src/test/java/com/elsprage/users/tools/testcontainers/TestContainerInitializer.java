package com.elsprage.users.tools.testcontainers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestContainerInitializer {

    public static void start(ContainerInitializer<?>... containerInitializers) {
        final Instant start = Instant.now();
        log.info("Initializing container.");
        Arrays.stream(containerInitializers).parallel().forEach(ContainerInitializer::startAndGet);
        log.info("TestContainers started in {} seconds", Duration.between(start, Instant.now()).toSeconds());
    }
}

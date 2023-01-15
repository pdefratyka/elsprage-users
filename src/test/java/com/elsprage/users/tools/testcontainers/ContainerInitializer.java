package com.elsprage.users.tools.testcontainers;

public interface ContainerInitializer<T> {
    T startAndGet();
}

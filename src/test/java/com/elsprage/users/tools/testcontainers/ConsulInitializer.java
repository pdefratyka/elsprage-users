package com.elsprage.users.tools.testcontainers;

import lombok.extern.log4j.Log4j2;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@Log4j2
public class ConsulInitializer implements ContainerInitializer<ConsulContainer> {

    public static final DockerImageName CONSUL_IMAGE = DockerImageName
            .parse("consul");
    private static ConsulContainer container;

    private static ConsulContainer getContainer() {
        if (container == null) {
            container = new ConsulContainer(CONSUL_IMAGE)
                    .withReuse(true);
            container.setPortBindings(List.of("8500:8500"));
        }
        return container;
    }

    @Override
    public ConsulContainer startAndGet() {
        getContainer().start();
        log.info("Consul start on host: {}, and port:{}", container.getHost(), container.getPortBindings());
        return getContainer();
    }
}

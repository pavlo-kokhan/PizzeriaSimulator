package org.example.pizzeriasimulator.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class StartupConfig {
    private final Environment environment;

    public StartupConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            String serverPort = environment.getProperty("server.port", "8080");
            String swaggerUrl = "http://localhost:" + serverPort + "/swagger-ui.html";
            System.out.println("Swagger UI is available at: " + swaggerUrl);
        };
    }
}

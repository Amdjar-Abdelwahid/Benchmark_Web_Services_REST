package com.example.serviceA.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages("com.example.serviceA.resources");
    }
}
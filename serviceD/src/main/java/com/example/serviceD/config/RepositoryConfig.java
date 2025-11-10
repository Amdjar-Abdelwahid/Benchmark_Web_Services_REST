package com.example.serviceD.config;

import com.example.serviceD.entities.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, org.springframework.web.servlet.config.annotation.CorsRegistry cors) {
        config.exposeIdsFor(Category.class, Item.class);
    }
}

package com.shpl.locations.config;

import com.shpl.locations.provider.DataProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LocationsConfig {

    @Bean
    public WebClient restTemplate(DataProperties dataProperties) {
        return WebClient.create(dataProperties.getBase());
    }

}

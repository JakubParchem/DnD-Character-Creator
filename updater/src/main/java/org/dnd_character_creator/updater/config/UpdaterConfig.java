package org.dnd_character_creator.updater.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UpdaterConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

package org.dnd_character_creator.updater;

import org.dnd_character_creator.updater.service.UpdaterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync
@ComponentScan(basePackages = {"org.dnd_character_creator.data", "org.dnd_character_creator.updater"})
@EnableJpaRepositories(basePackages = "org.dnd_character_creator.data.repository")
@EntityScan(basePackages = "org.dnd_character_creator.data.model")
@EnableScheduling
@SpringBootApplication
public class UpdaterApplication implements CommandLineRunner {
    private final UpdaterService updater;
    public UpdaterApplication(UpdaterService updater) {
        this.updater = updater;
    }
    public static void main(String[] args) {
        SpringApplication.run(UpdaterApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        updater.deleteAll();
        updater.updateAll();
    }
}

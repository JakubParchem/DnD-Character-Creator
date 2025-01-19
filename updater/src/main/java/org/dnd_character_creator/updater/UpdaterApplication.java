package org.dnd_character_creator.updater;


import org.dnd_character_creator.data.model.Class;
import org.dnd_character_creator.data.model.Spell;
import org.dnd_character_creator.data.model.Subclass;
import org.dnd_character_creator.data.model.Trait;
import org.dnd_character_creator.updater.client.DndApiClient;
import org.dnd_character_creator.updater.service.UpdaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@EnableAsync
@ComponentScan(basePackages = {"org.dnd_character_creator.data", "org.dnd_character_creator.updater"})
@EnableJpaRepositories(basePackages = "org.dnd_character_creator.data.repository")
@EntityScan(basePackages = "org.dnd_character_creator.data.model")
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
        updater.updateAll();
    }


//    public static void main(String[] args) {
//
//        SpringApplication.run(UpdaterApplication.class, args);
//    }
//    @Bean
//    CommandLineRunner run(){
//        return args -> {
//            System.out.println("Starting the updater...");
//            DndApiClient apiClient = new DndApiClient(new RestTemplate());
//            //List<Trait> traits=apiClient.getTraits();
//            //List<Spell> spells=apiClient.getSpells();
//            //List<Subclass> subclasses=apiClient.getSubclasses();
//            List<Class> classes=apiClient.getClasses();
//            UpdaterService updaterService=new UpdaterService(new DndApiClient(new RestTemplate()));
//            updaterService.updateAll();
//            System.out.println("Updater finished.");
//        };
//    }

}

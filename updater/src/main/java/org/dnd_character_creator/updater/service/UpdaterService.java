package org.dnd_character_creator.updater.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.dnd_character_creator.data.controller.*;
import org.dnd_character_creator.data.model.*;
import org.dnd_character_creator.data.model.Class;
import org.dnd_character_creator.data.repository.*;
import org.dnd_character_creator.updater.client.DndApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UpdaterService {

    private final DndApiClient apiClient;
    @Autowired
    private RaceController raceController;
    @Autowired
    private TraitController traitController;
    @Autowired
    private SpellController spellController;
    @Autowired
    private SubclassController subclassController;
    @Autowired
    private ClassController classController;
    @Autowired
    private SubraceController subraceController;
    @Autowired
    private SpellRepository spellRepository;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private TraitRepository traitRepository;
    @Autowired
    private SubclassRepository subclassRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private SubraceRepository subraceRepository;
    public UpdaterService(DndApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Transactional
    @Async
    public void updateTraits() throws JsonProcessingException {
        for(Trait trait:apiClient.getTraits()){
            traitController.updateTrait(trait.getId(),trait);
        }
    }
    public void updateSpells() throws JsonProcessingException {
        for(Spell spell:apiClient.getSpells()){
            spellController.updateSpell(spell.getId(), spell);
        }
    }
    public void updateSubclasses() throws JsonProcessingException {
        for(Subclass subclass:apiClient.getSubclasses()){
            subclassController.updateSubclass(subclass.getId(),subclass);
        }
    }
    public void updateClasses() throws JsonProcessingException {
        for(Class aclass:apiClient.getClasses()){
            classController.updateClass(aclass.getId(),aclass);
        }
    }
    public void updateSubraces() throws JsonProcessingException {
        for(Subrace subrace:apiClient.getSubraces()){
            subraceController.updateSubrace(subrace.getId(),subrace);
        }
    }
    public void updateRaces() throws JsonProcessingException {
        for(Race race:apiClient.getRaces()){
            raceController.updateRace(race.getId(), race);
        }
    }
    public void deleteAll() throws JsonProcessingException {
        raceRepository.deleteAll();
        classRepository.deleteAll();
        subclassRepository.deleteAll();
        subraceRepository.deleteAll();
        spellRepository.deleteAll();
        traitRepository.deleteAll();
    }
    @Scheduled(cron = "0 0 12 1 * ?")
    public void updateAll() throws JsonProcessingException {
        //deleteAll();
        updateSpells();
        updateTraits();
        updateSubclasses();
        updateSubraces();
        updateClasses();
        updateRaces();
    }
}

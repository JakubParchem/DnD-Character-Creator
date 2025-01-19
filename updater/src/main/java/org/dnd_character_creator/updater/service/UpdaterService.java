package org.dnd_character_creator.updater.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.dnd_character_creator.data.repository.*;
import org.dnd_character_creator.updater.client.DndApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdaterService {

    private final DndApiClient apiClient;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private TraitRepository traitRepository;
    @Autowired
    private SpellRepository spellRepository;
    @Autowired
    private SubclassRepository subclassRepository;
    @Autowired
    private ClassRepository classRepository;
    public UpdaterService(DndApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Transactional
    @Async
    public void updateTraits() throws JsonProcessingException {
        traitRepository.deleteAll();
        traitRepository.saveAll(apiClient.getTraits());
    }
    public void updateSpells() throws JsonProcessingException {
        spellRepository.deleteAll();
        spellRepository.saveAll(apiClient.getSpells());
    }
    public void updateSubclasses() throws JsonProcessingException {
        subclassRepository.deleteAll();
        subclassRepository.saveAll(apiClient.getSubclasses());
    }
    public void updateClasses() throws JsonProcessingException {
        classRepository.deleteAll();
        classRepository.saveAll(apiClient.getClasses());
    }

    public void updateAll() throws JsonProcessingException {
        updateSpells();
        updateTraits();
        updateSubclasses();
        //updateClasses();
    }
}

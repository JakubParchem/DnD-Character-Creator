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
    @Autowired
    private SubraceRepository subraceRepository;
    public UpdaterService(DndApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Transactional
    @Async
    public void updateTraits() throws JsonProcessingException {
        traitRepository.saveAll(apiClient.getTraits());
    }
    public void updateSpells() throws JsonProcessingException {
        spellRepository.saveAll(apiClient.getSpells());
    }
    public void updateSubclasses() throws JsonProcessingException {
        subclassRepository.saveAll(apiClient.getSubclasses());
    }
    public void updateClasses() throws JsonProcessingException {
        classRepository.saveAll(apiClient.getClasses());
    }
    public void updateSubraces() throws JsonProcessingException {
        subraceRepository.saveAll(apiClient.getSubraces());
    }
    public void updateRaces() throws JsonProcessingException {
        raceRepository.saveAll(apiClient.getRaces());
    }
    public void deleteAll() {
        raceRepository.deleteAll();
        classRepository.deleteAll();
        subclassRepository.deleteAll();
        subraceRepository.deleteAll();
        spellRepository.deleteAll();
        traitRepository.deleteAll();
    }

    public void updateAll() throws JsonProcessingException {
        deleteAll();
        updateSpells();
        updateTraits();
        updateSubclasses();
        updateSubraces();
        updateClasses();
        updateRaces();
    }
}

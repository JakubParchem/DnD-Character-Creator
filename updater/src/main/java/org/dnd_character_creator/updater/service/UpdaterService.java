package org.dnd_character_creator.updater.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.dnd_character_creator.data.repository.RaceRepository;
import org.dnd_character_creator.data.repository.SpellRepository;
import org.dnd_character_creator.data.repository.TraitRepository;
import org.dnd_character_creator.updater.client.DndApiClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdaterService {

    private final DndApiClient apiClient;
    private RaceRepository raceRepository;
    private TraitRepository traitRepository;
    private SpellRepository spellRepository;
    public UpdaterService(DndApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Transactional
    public void updateTraits() throws JsonProcessingException {
            traitRepository.deleteAllInBatch();
            traitRepository.saveAllAndFlush(apiClient.getTraits());
    }
}

package org.dnd_character_creator.data.controller;

import org.dnd_character_creator.data.model.Race;
import org.dnd_character_creator.data.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    @Autowired
    private RaceRepository raceRepository;

    @GetMapping
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable String id) {
        Optional<Race> race = raceRepository.findById(id);
        return race.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Race> createRace(@RequestBody Race race) {
        Race savedRace = raceRepository.save(race);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRace);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable String id, @RequestBody Race raceDetails) {
        Optional<Race> optionalRace = raceRepository.findById(id);
        if (optionalRace.isPresent()) {
            Race race = optionalRace.get();
            race.setName(raceDetails.getName());
            race.setDescription(raceDetails.getDescription());
            race.setSubraces(raceDetails.getSubraces());
            race.setTraits(raceDetails.getTraits());
            Race updatedRace = raceRepository.save(race);
            return ResponseEntity.ok(updatedRace);
        } else {
            return createRace(raceDetails);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable String id) {
        Optional<Race> race = raceRepository.findById(id);
        if (race.isPresent()) {
            raceRepository.delete(race.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

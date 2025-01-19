package org.dnd_character_creator.data.controller;

import org.dnd_character_creator.data.model.Trait;
import org.dnd_character_creator.data.repository.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/traits")
public class TraitController {

    @Autowired
    private TraitRepository traitRepository;

    @GetMapping
    public List<Trait> getAllTraits() {
        return traitRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trait> getTraitById(@PathVariable String id) {
        Optional<Trait> trait = traitRepository.findById(id);
        return trait.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Trait> createTrait(@RequestBody Trait trait) {
        Trait savedTrait = traitRepository.save(trait);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTrait);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trait> updateTrait(@PathVariable String id, @RequestBody Trait traitDetails) {
        Optional<Trait> optionalTrait = traitRepository.findById(id);

        if (optionalTrait.isPresent()) {
            Trait trait = optionalTrait.get();
            trait.setName(traitDetails.getName());
            trait.setDescription(traitDetails.getDescription());
            Trait updatedTrait = traitRepository.save(trait);
            return ResponseEntity.ok(updatedTrait);
        } else {
            return createTrait(traitDetails);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrait(@PathVariable String id) {
        Optional<Trait> trait = traitRepository.findById(id);
        if (trait.isPresent()) {
            traitRepository.delete(trait.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

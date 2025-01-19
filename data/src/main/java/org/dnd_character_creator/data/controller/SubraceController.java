package org.dnd_character_creator.data.controller;

import org.dnd_character_creator.data.model.Subrace;
import org.dnd_character_creator.data.repository.SubraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subraces")
public class SubraceController {

    @Autowired
    private SubraceRepository subraceRepository;

    @GetMapping
    public List<Subrace> getAllSubraces() {
        return subraceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subrace> getSubraceById(@PathVariable String id) {
        Optional<Subrace> subrace = subraceRepository.findById(id);
        return subrace.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subrace> createSubrace(@RequestBody Subrace subrace) {
        Subrace savedSubrace = subraceRepository.save(subrace);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubrace);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subrace> updateSubrace(@PathVariable String id, @RequestBody Subrace subraceDetails) {
        Optional<Subrace> optionalSubrace = subraceRepository.findById(id);

        if (optionalSubrace.isPresent()) {
            Subrace subrace = optionalSubrace.get();
            subrace.setName(subraceDetails.getName());
            subrace.setDescription(subraceDetails.getDescription());
            subrace.setTraits(subraceDetails.getTraits());
            Subrace updatedSubrace = subraceRepository.save(subrace);
            return ResponseEntity.ok(updatedSubrace);
        } else {
            return createSubrace(subraceDetails);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubrace(@PathVariable String id) {
        Optional<Subrace> subrace = subraceRepository.findById(id);
        if (subrace.isPresent()) {
            subraceRepository.delete(subrace.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

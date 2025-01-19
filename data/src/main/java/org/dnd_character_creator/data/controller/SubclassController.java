package org.dnd_character_creator.data.controller;

import org.dnd_character_creator.data.model.Subclass;
import org.dnd_character_creator.data.repository.SubclassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subclasses")
public class SubclassController {

    @Autowired
    private SubclassRepository subclassRepository;

    @GetMapping
    public List<Subclass> getAllSubclasses() {
        return subclassRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subclass> getSubclassById(@PathVariable String id) {
        Optional<Subclass> subclass = subclassRepository.findById(id);
        return subclass.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subclass> createSubclass(@RequestBody Subclass subclass) {
        Subclass savedSubclass = subclassRepository.save(subclass);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubclass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subclass> updateSubclass(@PathVariable String id, @RequestBody Subclass subclassDetails) {
        Optional<Subclass> optionalSubclass = subclassRepository.findById(id);

        if (optionalSubclass.isPresent()) {
            Subclass subclass = optionalSubclass.get();
            subclass.setName(subclassDetails.getName());
            subclass.setDescription(subclassDetails.getDescription());
            subclass.setSpells(subclassDetails.getSpells());
            Subclass updatedSubclass = subclassRepository.save(subclass);
            return ResponseEntity.ok(updatedSubclass);
        } else {
            return createSubclass(subclassDetails);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubclass(@PathVariable String id) {
        Optional<Subclass> subclass = subclassRepository.findById(id);
        if (subclass.isPresent()) {
            subclassRepository.delete(subclass.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

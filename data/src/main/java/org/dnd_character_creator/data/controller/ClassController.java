package org.dnd_character_creator.data.controller;

import org.dnd_character_creator.data.model.Class;
import org.dnd_character_creator.data.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    @GetMapping
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable String id) {
        Optional<Class> classObj = classRepository.findById(id);
        return classObj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Class> createClass(@RequestBody Class classObj) {
        Class savedClass = classRepository.save(classObj);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable String id, @RequestBody Class classDetails) {
        Optional<Class> optionalClass = classRepository.findById(id);

        if (optionalClass.isPresent()) {
            Class classObj = optionalClass.get();
            classObj.setName(classDetails.getName());
            classObj.setHit_die(classDetails.getHit_die());
            classObj.setSubclasses(classDetails.getSubclasses());
            classObj.setSpells(classDetails.getSpells());
            Class updatedClass = classRepository.save(classObj);
            return ResponseEntity.ok(updatedClass);
        } else {
            return createClass(classDetails);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable String id) {
        Optional<Class> classObj = classRepository.findById(id);
        if (classObj.isPresent()) {
            classRepository.delete(classObj.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

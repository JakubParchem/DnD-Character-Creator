package org.dnd_character_creator.data.controller;

import org.dnd_character_creator.data.model.Character;
import org.dnd_character_creator.data.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Optional<Character> character = characterRepository.findById(id);
        return character.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        Character savedCharacter = characterRepository.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character characterDetails) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            Character character = optionalCharacter.get();
            character.setName(characterDetails.getName());
            character.setaClass(characterDetails.getaClass());
            character.setSubclass(characterDetails.getSubclass());
            character.setRace(characterDetails.getRace());
            character.setSubrace(characterDetails.getSubrace());
            character.setSpells(characterDetails.getSpells());
            character.setTraits(characterDetails.getTraits());
            Character updatedCharacter = characterRepository.save(character);
            return ResponseEntity.ok(updatedCharacter);
        } else {
            return createCharacter(characterDetails);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        Optional<Character> character = characterRepository.findById(id);
        if (character.isPresent()) {
            characterRepository.delete(character.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

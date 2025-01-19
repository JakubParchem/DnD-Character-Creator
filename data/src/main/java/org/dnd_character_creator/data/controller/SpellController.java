package org.dnd_character_creator.data.controller;

import org.dnd_character_creator.data.model.Spell;
import org.dnd_character_creator.data.repository.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spells")
public class SpellController {

    @Autowired
    private SpellRepository spellRepository;

    @GetMapping
    public List<Spell> getAllSpells() {
        return spellRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spell> getSpellById(@PathVariable String id) {
        Optional<Spell> spell = spellRepository.findById(id);
        return spell.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Spell> createSpell(@RequestBody Spell spell) {
        Spell savedSpell = spellRepository.save(spell);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSpell);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spell> updateSpell(@PathVariable String id, @RequestBody Spell spellDetails) {
        Optional<Spell> optionalSpell = spellRepository.findById(id);

        if (optionalSpell.isPresent()) {
            Spell spell = optionalSpell.get();
            spell.setName(spellDetails.getName());
            spell.setDescription(spellDetails.getDescription());
            Spell updatedSpell = spellRepository.save(spell);
            return ResponseEntity.ok(updatedSpell);
        } else {
            return createSpell(spellDetails);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpell(@PathVariable String id) {
        Optional<Spell> spell = spellRepository.findById(id);
        if (spell.isPresent()) {
            spellRepository.delete(spell.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

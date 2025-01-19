package org.example.dnd_character_creator.data.repository;

import org.example.dnd_character_creator.data.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellRepository extends JpaRepository<Spell, String> {
}

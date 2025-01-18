package org.example.dnd_character_creator.repository;

import org.example.dnd_character_creator.model.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpellRepository extends JpaRepository<Spell, String> {
}

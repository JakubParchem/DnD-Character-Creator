package org.example.dnd_character_creator.repository;

import org.example.dnd_character_creator.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}

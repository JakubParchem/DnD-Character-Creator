package org.example.dnd_character_creator.repository;

import org.example.dnd_character_creator.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
}

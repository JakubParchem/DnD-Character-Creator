package org.example.dnd_character_creator.data.repository;

import org.example.dnd_character_creator.data.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
}

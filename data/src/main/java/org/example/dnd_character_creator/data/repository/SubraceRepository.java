package org.example.dnd_character_creator.data.repository;

import org.example.dnd_character_creator.data.model.Subrace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubraceRepository extends JpaRepository<Subrace, String> {
}

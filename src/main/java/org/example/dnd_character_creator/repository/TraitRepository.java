package org.example.dnd_character_creator.repository;

import org.example.dnd_character_creator.model.Trait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitRepository extends JpaRepository<Trait, String> {
}

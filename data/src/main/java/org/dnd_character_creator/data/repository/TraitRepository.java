package org.dnd_character_creator.data.repository;

import org.dnd_character_creator.data.model.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitRepository extends JpaRepository<Trait, String> {
}

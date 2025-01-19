package org.example.dnd_character_creator.data.repository;

import org.example.dnd_character_creator.data.model.Subclass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubclassRepository extends JpaRepository<Subclass, String> {
}

package org.example.dnd_character_creator.repository;

import org.example.dnd_character_creator.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, String> {

}

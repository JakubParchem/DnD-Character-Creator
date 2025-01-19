package org.example.dnd_character_creator.data.repository;

import org.example.dnd_character_creator.data.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, String> {

}

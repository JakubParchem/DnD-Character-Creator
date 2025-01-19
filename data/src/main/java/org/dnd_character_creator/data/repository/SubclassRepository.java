package org.dnd_character_creator.data.repository;

import org.dnd_character_creator.data.model.Subclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubclassRepository extends JpaRepository<Subclass, String> {
}

package org.example.dnd_character_creator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Race {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

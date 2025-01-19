package org.dnd_character_creator.data.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Spell {
    @Id
    private String id;

    @Column
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

//    @ManyToMany
//    private List<Subclass> subclasses;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

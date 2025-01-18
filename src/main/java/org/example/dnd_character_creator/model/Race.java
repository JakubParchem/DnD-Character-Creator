package org.example.dnd_character_creator.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Race {
    @Id
    private String id;
    @Column
    private String name;

    private String description;

    @OneToMany(mappedBy = "race")
    private List<Subrace> subraces;

    @ManyToMany
    private List<Trait> traits;
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

    public List<Subrace> getSubraces() {
        return subraces;
    }

    public void setSubraces(List<Subrace> subraces) {
        this.subraces = subraces;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }
}

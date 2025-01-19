package org.example.dnd_character_creator.data.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Subrace {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "subrace_traits",
            joinColumns = @JoinColumn(name = "subraces_id"),
            inverseJoinColumns = @JoinColumn(name = "traits_id")
    )
    private List<Trait> traits;
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;
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

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }
}

package org.dnd_character_creator.data.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Trait {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String description;

//    @ManyToMany
//    private List<Subrace> subraces;

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

    public void setDescription(String desc) {
        this.description = desc;
    }
}

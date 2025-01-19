package org.example.dnd_character_creator.data.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Class {
    @Id
    private String id;

    @Column
    private String name;

    private Short hit_die;

    @OneToMany(mappedBy = "aClass")
    private List<Subclass> subclasses;

    @ManyToMany
    private List<Spell> spells;

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

    public Short getHit_die() {
        return hit_die;
    }

    public void setHit_die(Short hit_die) {
        this.hit_die = hit_die;
    }

    public List<Subclass> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<Subclass> subclasses) {
        this.subclasses = subclasses;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
}

package org.example.dnd_character_creator.data.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Character {
    @Id
    private Long id;
    @Column
    private String name;
    @OneToOne
    private Class aClass;
    @OneToOne
    private Subclass subclass;
    @OneToOne
    private Race race;
    @OneToOne
    private Subrace subrace;
    @ManyToMany
    private List<Spell> spells;
    @ManyToMany
    private List<Trait> traits;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Subclass getSubclass() {
        return subclass;
    }

    public void setSubclass(Subclass subclass) {
        this.subclass = subclass;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
    public Subrace getSubrace() {
        return subrace;
    }
    public void setSubrace(Subrace subrace) {
        this.subrace = subrace;
    }
    public List<Spell> getSpells() {
        return spells;
    }
    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
    public List<Trait> getTraits() {
        return traits;
    }
    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }
}

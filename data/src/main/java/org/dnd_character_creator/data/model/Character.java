package org.dnd_character_creator.data.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    private Class aClass;
    @ManyToOne
    private Subclass subclass;
    @ManyToOne
    private Race race;
    @ManyToOne
    private Subrace subrace;
    @ManyToMany
    private List<Spell> spells=new ArrayList<>();
    @ManyToMany
    private List<Trait> traits=new ArrayList<>();
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

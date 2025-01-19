package org.dnd_character_creator.data.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CharacterDataCatalog {
    private final ClassRepository classes;
    private final SubclassRepository subclasses;
    private final RaceRepository races;
    private final SpellRepository spells;
    private final SubraceRepository subraces;
    private final TraitRepository traits;
    private final CharacterRepository characters;

    public CharacterDataCatalog(ClassRepository classes, SubclassRepository subclasses, RaceRepository races, SpellRepository spells, SubraceRepository subraces, TraitRepository traits, CharacterRepository characters) {
        this.classes = classes;
        this.subclasses = subclasses;
        this.races = races;
        this.spells = spells;
        this.subraces = subraces;
        this.traits = traits;
        this.characters = characters;
    }

    public ClassRepository getClasses() {
        return classes;
    }

    public SubclassRepository getSubclasses() {
        return subclasses;
    }

    public RaceRepository getRaces() {
        return races;
    }

    public SpellRepository getSpells() {
        return spells;
    }

    public SubraceRepository getSubraces() {
        return subraces;
    }

    public TraitRepository getTraits() {
        return traits;
    }

    public CharacterRepository getCharacters() {
        return characters;
    }
}

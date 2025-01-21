package org.dnd_character_creator.frontend.data.controller;

import org.dnd_character_creator.data.model.*;
import org.dnd_character_creator.data.model.Character;
import org.dnd_character_creator.data.model.Class;
import org.dnd_character_creator.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ComponentScan(basePackages = {"org.dnd_character_creator.data"})
@EnableJpaRepositories(basePackages = "org.dnd_character_creator.data.repository")
@EntityScan(basePackages = "org.dnd_character_creator.data.model")
@Controller
public class FrontendController {

    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private SubclassRepository subclassRepository;
    @Autowired
    private SubraceRepository subraceRepository;
    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping("/create-character")
    public String showCharacterForm(Model model) {
        Character character = new Character();

        List<Race> races = raceRepository.findAll();
        List<Class> classes = classRepository.findAll();

        model.addAttribute("character", character);
        model.addAttribute("races", races);
        model.addAttribute("classes", classes);

        return "create-character";
    }

    @GetMapping("/get-subraces")
    public String getSubraces(@RequestParam String raceId, Model model) {
        List<Subrace> subraces = raceRepository.findById(raceId).get().getSubraces();
        model.addAttribute("subraces", subraces);
        return "subraces-fragment";
    }

    @GetMapping("/get-subclasses")
    public String getSubclasses(@RequestParam String classId, Model model) {
        List<Subclass> subclasses = classRepository.findById(classId).get().getSubclasses();
        model.addAttribute("subclasses", subclasses);
        return "subclasses-fragment";
    }

    @PostMapping("/create-character")
    public String createCharacter(@RequestParam("name") String name,
                                  @RequestParam("raceId") String raceId,
                                  @RequestParam("classId") String classId,
                                  @RequestParam("subraceId") String subraceId,
                                  @RequestParam("subclassId") String subclassId) {
        Race race = raceRepository.findById(raceId).orElse(null);
        Class aClass = classRepository.findById(classId).orElse(null);
        Subrace subrace = subraceRepository.findById(subraceId).orElse(null);
        Subclass subclass = subclassRepository.findById(subclassId).orElse(null);

        List<Trait> traits=new ArrayList<>();
        List<Spell> spells=new ArrayList<>();
        Character character = new Character();
        character.setName(name);
        character.setRace(race);
        character.setaClass(aClass);
        if(!race.getSubraces().isEmpty()) {
            character.setSubrace(subrace);
        }
        else{
            character.setSubrace(null);
        }
        character.setSubclass(subclass);
        if(race!=null){
            traits.addAll(race.getTraits());
        }
        if(subrace!=null){
            Set<Trait> set = new HashSet<>(traits);
            set.addAll(subrace.getTraits());
            traits=new ArrayList<>(set);
        }
        character.setTraits(traits);
        if(aClass!=null){
            spells.addAll(aClass.getSpells());
        }
        if(subclass!=null){
            Set<Spell> set = new HashSet<>(spells);
            set.addAll(subclass.getSpells());
            spells=new ArrayList<>(set);
        }
        character.setSpells(spells);

        return "redirect:/character-profile?character_id="+characterRepository.save(character).getId();
    }
    @GetMapping("/character-profile")
    public String characterProfile(@RequestParam("character_id")Long id, Model model) {
        Character character = characterRepository.findById(id).get();

        model.addAttribute("name",character.getName());

        model.addAttribute("race", character.getRace().getName());
        model.addAttribute("raceDescription", character.getRace().getDescription());
        if(character.getSubrace()!=null) {
            model.addAttribute("subrace", character.getSubrace().getName());
            model.addAttribute("subraceDescription", character.getSubrace().getDescription());
        }

        model.addAttribute("class", character.getaClass().getName());
        model.addAttribute("subclass", character.getSubclass().getName());
        model.addAttribute("subclassDescription", character.getSubclass().getDescription());

        model.addAttribute("spells", character.getSpells());
        model.addAttribute("traits", character.getTraits());

        return "character-profile";
    }
}

package org.dnd_character_creator.updater.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dnd_character_creator.data.model.Class;
import org.dnd_character_creator.data.model.Spell;
import org.dnd_character_creator.data.model.Subclass;
import org.dnd_character_creator.data.model.Trait;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class DndApiClient {

    private final RestTemplate restTemplate;
    ObjectMapper objectMapper = new ObjectMapper();
    int count;
    String description,url,response;
    List<Spell> spells=new ArrayList<>();
    List<Trait> traits=new ArrayList<>();
    List<Subclass> subclasses=new ArrayList<>();
    JsonNode rootNode,resultsNode,results1Node;
    private final String baseUrl = "https://www.dnd5eapi.co";

    public DndApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<Trait> getTraits() throws JsonProcessingException {
        url=baseUrl + "/api/traits";
        response=restTemplate.getForObject(url, String.class);
        rootNode = objectMapper.readTree(response);
        count = rootNode.get("count").asInt();
        resultsNode = rootNode.get("results");
        List<String> urls = new ArrayList<>();
        for (JsonNode result : resultsNode) {
            urls.add(result.get("url").asText());
        }
        traits=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Trait trait=new Trait();
            System.out.println("Traits: [ "+(i+1)+" / "+count+" ]\n");
            response=restTemplate.getForObject(baseUrl + urls.get(i), String.class);
            rootNode = objectMapper.readTree(response);
            trait.setName(rootNode.get("name").asText());
            trait.setId(rootNode.get("index").asText());
            resultsNode=rootNode.get("desc");
            description ="";
            for (JsonNode result : resultsNode) {
                description +=result.asText();
            }
            trait.setDescription(description);
            traits.addLast(trait);
        }
        return traits;
    }
    public List<Spell> getSpells() throws JsonProcessingException {
        url=baseUrl + "/api/spells";
        response=restTemplate.getForObject(url, String.class);
        rootNode = objectMapper.readTree(response);
        count = rootNode.get("count").asInt();
        resultsNode = rootNode.get("results");
        List<String> urls = new ArrayList<>();
        for (JsonNode result : resultsNode) {
            urls.add(result.get("url").asText());
        }
        spells=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Spell spell=new Spell();
            System.out.println("Spells: [ "+(i+1)+" / "+count+" ]\n");
            response=restTemplate.getForObject(baseUrl + urls.get(i), String.class);
            rootNode = objectMapper.readTree(response);
            spell.setName(rootNode.get("name").asText());
            spell.setId(rootNode.get("index").asText());
            resultsNode=rootNode.get("desc");
            description ="";
            for (JsonNode result : resultsNode) {
                description +=result.asText();
            }
            spell.setDescription(description);
            spells.add(spell);
        }
        return spells;
    }

    public List<Subclass> getSubclasses() throws JsonProcessingException {
        if(spells.isEmpty()){
            spells=getSpells();
        }
        url=baseUrl + "/api/subclasses";
        response=restTemplate.getForObject(url, String.class);
        rootNode = objectMapper.readTree(response);
        count = rootNode.get("count").asInt();
        resultsNode = rootNode.get("results");
        List<String> urls = new ArrayList<>();
        for (JsonNode result : resultsNode) {
            urls.add(result.get("url").asText());
        }
        subclasses=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Subclass subclass=new Subclass();
            System.out.println("Subclasses: [ "+(i+1)+" / "+count+" ]\n");
            response=restTemplate.getForObject(baseUrl + urls.get(i), String.class);
            rootNode = objectMapper.readTree(response);
            subclass.setName(rootNode.get("name").asText());
            subclass.setId(rootNode.get("index").asText());
            resultsNode=rootNode.get("desc");
            description ="";
            for (JsonNode result : resultsNode) {
                description +=result.asText();
            }
            subclass.setDescription(description);
            resultsNode=rootNode.get("spells");
            if(!resultsNode.isEmpty()) {
                for (JsonNode result : resultsNode) {
                    results1Node = result.get("spell");
                    subclass.getSpells().add(spells.stream().
                            filter(spell -> spell.getId()
                                    .equals(results1Node.get("index").asText()))
                            .findFirst().get());
                }
            }
            subclasses.add(subclass);
        }
        return subclasses;
    }
    public List<Class> getClasses() throws JsonProcessingException {
        if(spells.isEmpty()){
            spells=getSpells();
        }
        if(subclasses.isEmpty()){
            subclasses=getSubclasses();
        }
        url=baseUrl + "/api/classes";
        response=restTemplate.getForObject(url, String.class);
        rootNode = objectMapper.readTree(response);
        count = rootNode.get("count").asInt();
        resultsNode = rootNode.get("results");
        List<String> urls = new ArrayList<>();
        for (JsonNode result : resultsNode) {
            urls.add(result.get("url").asText());
        }
        List<Class> classes=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("Classes: [ "+(i+1)+" / "+count+" ]\n");
            Class aclass=new Class();
            response=restTemplate.getForObject(baseUrl + urls.get(i), String.class);
            rootNode = objectMapper.readTree(response);
            aclass.setName(rootNode.get("name").asText());
            aclass.setId(rootNode.get("index").asText());
            aclass.setHit_die(rootNode.get("hit_die").asInt());
            if(rootNode.get("spells")!=null) {
                resultsNode = rootNode.get("spells");
                for (JsonNode result : resultsNode) {
                    results1Node = result.get("spell");
                    aclass.getSpells().add(spells.stream().
                            filter(spell -> spell.getId()
                                    .equals(results1Node.get("index").asText()))
                            .findFirst().get());
                }
            }
            if(rootNode.get("subclasses")!=null) {
                resultsNode = rootNode.get("subclasses");
                for (JsonNode result : resultsNode) {
                    aclass.getSubclasses().add(subclasses.stream().
                            filter(subclass -> subclass.getId()
                                    .equals(result.get("index").asText()))
                            .findFirst().get());
                }
            }
            classes.add(aclass);
        }
        return classes;
    }
}

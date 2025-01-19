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
        Trait trait=new Trait();
        List<Trait> traits=new ArrayList<>();
        for (int i = 0; i < count; i++) {
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
            traits.add(trait);
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
        Spell spell=new Spell();
        List<Spell> spells=new ArrayList<>();
        for (int i = 0; i < count; i++) {
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
        List<Spell> spells=getSpells();
        url=baseUrl + "/api/subclasses";
        response=restTemplate.getForObject(url, String.class);
        rootNode = objectMapper.readTree(response);
        count = rootNode.get("count").asInt();
        resultsNode = rootNode.get("results");
        List<String> urls = new ArrayList<>();
        for (JsonNode result : resultsNode) {
            urls.add(result.get("url").asText());
        }
        Subclass subclass=new Subclass();
        List<Subclass> subclasses=new ArrayList<>();
        for (int i = 0; i < count; i++) {
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
            for (JsonNode result : resultsNode) {
                results1Node=result.get("spell");
                subclass.getSpells().add(spells.stream().
                        filter(spell -> spell.getId()
                        .equals(results1Node.get("index").asText()))
                        .findFirst().get());
            }
            subclasses.add(subclass);
        }
        return subclasses;
    }
    public List<Class> getClasses() throws JsonProcessingException {
        List<Spell> spells=getSpells();
        List<Subclass> subclasses=getSubclasses();
        url=baseUrl + "/api/classes";
        response=restTemplate.getForObject(url, String.class);
        rootNode = objectMapper.readTree(response);
        count = rootNode.get("count").asInt();
        resultsNode = rootNode.get("results");
        List<String> urls = new ArrayList<>();
        for (JsonNode result : resultsNode) {
            urls.add(result.get("url").asText());
        }
        Class aclass=new Class();
        List<Class> classes=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            response=restTemplate.getForObject(baseUrl + urls.get(i), String.class);
            rootNode = objectMapper.readTree(response);
            aclass.setName(rootNode.get("name").asText());
            aclass.setId(rootNode.get("index").asText());

            resultsNode=rootNode.get("spells");
            for (JsonNode result : resultsNode) {
                results1Node=result.get("spell");
                aclass.getSpells().add(spells.stream().
                        filter(spell -> spell.getId()
                                .equals(results1Node.get("index").asText()))
                        .findFirst().get());
            }
            classes.add(aclass);
        }
        return classes;
    }
}

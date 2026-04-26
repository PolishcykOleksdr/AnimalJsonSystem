package com.tz.task1;

import com.tz.task1.conditions.ConditionFactory;
import com.tz.task1.rule.Rule;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */
public class DataLoader {
    private final Path animalsPath;
    private final Path propertiesPath;
    private final Path rulesPath;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DataLoader(Path animalsPat, Path propertyPath, Path rulesPath) {
        this.animalsPath = animalsPat;
        this.propertiesPath = propertyPath;
        this.rulesPath = rulesPath;
    }

    @SneakyThrows
    private Map<String, List<String>> loadProperties(){
        Map<String, List<String>> properties = new HashMap<>();

        JsonNode node = objectMapper.readTree(propertiesPath.toFile());

        node.fields().forEachRemaining(entry -> {
            List<String> values = new ArrayList<>();
            entry.getValue().forEach(value -> values.add(value.asText()));
            properties.put(entry.getKey(), values);
        });

        return properties;
    }

    @SneakyThrows
    public List<Map<String, String>> loadAnimals(){
        Map<String, List<String>> properties = loadProperties();
        JsonNode root = objectMapper.readTree(animalsPath.toFile());

        List<Map<String, String>> animals = new ArrayList<>();
        for(JsonNode node : root){
            Map<String, String> animal = new HashMap<>();
            node.fields().forEachRemaining(entry -> {
                animal.put(entry.getKey(), entry.getValue().asText());
            });
            validate(animal, properties);
            animals.add(animal);
        }
        return animals;
    }

    @SneakyThrows
    public List<Rule> loadRules(){
        List<Rule> rules = new ArrayList<>();
        JsonNode root = objectMapper.readTree(rulesPath.toFile());

        for(JsonNode node : root){
            rules.add(new Rule(
                    node.get("name").asText(),
                    ConditionFactory.build(node.get("condition"))
            ));
        }

        return rules;
    }

    private void validate(Map<String, String> animals, Map<String, List<String>> properties){
        for(Map.Entry<String, String> entry : animals.entrySet()){
            String property = entry.getKey();
            String value = entry.getValue();

            if(!properties.containsKey(property)){
                throw new RuntimeException("Missing property: " + property);
            }
            if(!properties.get(property).contains(value)){
                throw new RuntimeException("Missing value: " + property);
            }
        }
    }
}
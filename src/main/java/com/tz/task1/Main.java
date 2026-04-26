package com.tz.task1;

import com.tz.task1.rule.Rule;
import com.tz.task1.rule.RuleEngine;
import com.tz.task1.rule.RuleResult;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */
public class Main {
    private static final Path DATA_DIR = Path.of("data");

    public static void main(String[] args) throws IOException {
        DataLoader dataLoader = new DataLoader(
                DATA_DIR.resolve("animals.json"),
                DATA_DIR.resolve("properties.json"),
                DATA_DIR.resolve("rules.json")
        );

        List<Map<String, String>> animals = dataLoader.loadAnimals();
        List<Rule> rules = dataLoader.loadRules();

        List<RuleResult> ruleResults = new RuleEngine().evaluate(animals, rules);

        new Reporter().report(ruleResults);
    }
}

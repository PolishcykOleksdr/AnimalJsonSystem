package com.tz.task1.rule;

import com.tz.task1.conditions.Condition;
import lombok.Getter;

import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */

public class Rule {
    @Getter
    private final String name;
    private final Condition condition;

    public Rule(String name, Condition condition) {
        this.name = name;
        this.condition = condition;
    }

    public boolean matches(Map<String, String> animal) {
        return condition.matches(animal);
    }
}
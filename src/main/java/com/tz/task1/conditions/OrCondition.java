package com.tz.task1.conditions;

import java.util.List;
import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */
public class OrCondition implements Condition {
    private final List<Condition> conditions;

    public OrCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean matches(Map<String, String> animal) {
        return conditions.stream().anyMatch(c -> c.matches(animal));
    }
}

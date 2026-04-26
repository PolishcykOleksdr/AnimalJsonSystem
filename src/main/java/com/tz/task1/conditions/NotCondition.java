package com.tz.task1.conditions;

import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */
public class NotCondition implements Condition {
    private final Condition condition;

    public NotCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public boolean matches(Map<String, String> animal) {
        return !condition.matches(animal);
    }
}

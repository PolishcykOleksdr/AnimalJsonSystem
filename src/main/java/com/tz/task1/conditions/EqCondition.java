package com.tz.task1.conditions;

import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */
public class EqCondition implements Condition {
    private final String property;
    private final String value;

    public EqCondition(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public boolean matches(Map<String, String> animal) {
        return value.equals(animal.get(property));
    }
}

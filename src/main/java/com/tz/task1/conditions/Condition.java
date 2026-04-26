package com.tz.task1.conditions;

import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */
public interface Condition {
    boolean matches(Map<String, String> animal);
}
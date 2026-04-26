package com.tz.task1.conditions;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * author: user,
 * date: 26.04.2026
 */

public class ConditionFactory {
    public static Condition build(JsonNode node) {
        String op = node.get("op").asText();

        return switch (op) {
            case "EQ" -> buildEq(node);
            case "AND" -> buildAnd(node);
            case "OR" -> buildOr(node);
            case "NOT" -> buildNot(node);
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }
    private static EqCondition buildEq(JsonNode node) {
        return new EqCondition(
                node.get("property").asText(),
                node.get("value").asText()
        );
    }
    private static AndCondition buildAnd(JsonNode node) {
        return new AndCondition(buildChildren(node));
    }
    private static OrCondition buildOr(JsonNode node) {
        return new OrCondition(buildChildren(node));
    }
    private static NotCondition buildNot(JsonNode node) {
        return new NotCondition(build(node.get("condition")));
    }
    private static List<Condition> buildChildren(JsonNode node) {
        List<Condition> result = new ArrayList<>();
        for(JsonNode child : node.get("conditions")) {
            result.add(build(child));
        }
        return result;
    }
}
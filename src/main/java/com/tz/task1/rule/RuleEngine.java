package com.tz.task1.rule;

import java.util.List;
import java.util.Map;

/**
 * author: user,
 * date: 26.04.2026
 */
public class RuleEngine {
    public List<RuleResult> evaluate(
            List<Map<String, String>> animals,
            List<Rule> rules
    ){
        return rules.stream().map(
                rule -> (new RuleResult(
                        rule.getName(),
                        (int) animals.stream().filter(rule::matches).count()
                    )
                )
        ).toList();
    }
}
package com.tz.task1.rule;

import lombok.Getter;

/**
 * author: user,
 * date: 26.04.2026
 */

@Getter
public class RuleResult {
    private final String ruleName;
    private final int count;

    public RuleResult(String ruleName, int count) {
        this.ruleName = ruleName;
        this.count = count;
    }
}
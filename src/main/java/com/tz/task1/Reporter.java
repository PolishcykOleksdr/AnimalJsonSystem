package com.tz.task1;

import com.tz.task1.rule.RuleResult;

import java.util.List;

/**
 * author: user,
 * date: 26.04.2026
 */
public class Reporter {
    public void report(List<RuleResult> ruleResults) {
        System.out.println("Results:");
        for (int i = 0; i < ruleResults.size(); i++) {
            System.out.printf("%d. %s: %d%n",
                    i + 1,
                    ruleResults.get(i).getRuleName(),
                    ruleResults.get(i).getCount()
            );
        }
    };
}

package com.example.decision_engine.dto; //dto -- Data Transfer Object

/// It exists ONLY to: Transfer data, Shape response JSON, Provide explainability, Same for: DecisionReport
/// this file for Per-Rule Output

public class RuleResult {
    private String ruleName;
    private int score;
    private String expression;

    //getter and setter


    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}

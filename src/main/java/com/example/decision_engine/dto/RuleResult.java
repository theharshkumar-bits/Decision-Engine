package com.example.decision_engine.dto; //dto -- Data Transfer Object

/// It exists ONLY to: Transfer data, Shape response JSON, Provide explainability, Same for: DecisionReport
/// this file for Per-Rule Output

public class RuleResult {
    private String ruleName;
    private int score;
    private String expression;

    //for rule validation
    private String status;
    private String errorMessage;

    //adding weight for calculating the score
    private int weight;
    private int weightedScore;

//getter and setter

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeightedScore() {
        return weightedScore;
    }

    public void setWeightedScore(int weightedScore) {
        this.weightedScore = weightedScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }





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

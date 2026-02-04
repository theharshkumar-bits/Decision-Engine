package com.example.decision_engine.dto;

import java.util.List;

/// final output-- this give Explainability

public class DecisionReport {
    private int finalScore;
    //for making DECISION like ACCEPT, HOLD, REJECT
    private String decision;
    private List<RuleResult> breakdown;

    //getters and setters


    public List<RuleResult> getBreakdown() {
        return breakdown;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public void setBreakdown(List<RuleResult> breakdown) {
        this.breakdown = breakdown;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}

package com.example.decision_engine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rule_results")
public class RuleResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private int score;
    @Column(length = 1000)
    private String expression;

    private String status;
    @Column(length = 1000)
    private String errorMessage;


    @ManyToOne
    @JoinColumn(name = "report_id")
    private EvaluationReportEntity report;

    //getter and setter

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getScore() {
        return score;
    }

    public String getExpression() {
        return expression;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public EvaluationReportEntity getReport() {
        return report;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setReport(EvaluationReportEntity report) {
        this.report = report;
    }
}

package com.example.decision_engine.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="evaluation_reports")

public class EvaluationReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int finalScore;

    private LocalDateTime createdAt;
                           //childname
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<RuleResultEntity> results;


    //getter and setter
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<RuleResultEntity> getResults() {
        return results;
    }

    public void setResults(List<RuleResultEntity> results) {
        this.results = results;
    }
}

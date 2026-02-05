package com.example.decision_engine.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="rules")
public class RuleEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String expression;   //SpEL expression


    private int weight;

    private boolean active;   ///for feature toggling
    // adding priority
    private Integer priority; // changed int to Integer  because int can't store null for already store data in database for this column for existing row value will be null

    //for rule versioning
    private Integer version;
    private LocalDateTime effectiveFrom;
    private LocalDateTime effectiveTo;


    //getters  setters  @Data (automatically work for getter and setter Lombok Shortcut)

    public LocalDateTime getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(LocalDateTime effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDateTime effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }





    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

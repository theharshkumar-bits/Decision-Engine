package com.example.decision_engine.controller;

import com.example.decision_engine.dto.DecisionReport;
import com.example.decision_engine.engine.RuleEvaluationEngine;
import com.example.decision_engine.model.Candidate;
import org.springframework.web.bind.annotation.*;


/// This controller is NOT part of final product just testing i am using it
/// purpose  verify SpEl logic, Verify Scoring ,Verify Rule Execution, Debug Expression easily


@RestController
@RequestMapping("/api/test")
public class EnginTestController {
    private final RuleEvaluationEngine ruleEvaluationEngine;

    public EnginTestController(RuleEvaluationEngine ruleEvaluationEngine) {
        this.ruleEvaluationEngine = ruleEvaluationEngine;
    }

    @PostMapping("/evaluate")
    public DecisionReport evaluate(@RequestBody Candidate candidate) {
        return ruleEvaluationEngine.evaluate(candidate);
    }

}

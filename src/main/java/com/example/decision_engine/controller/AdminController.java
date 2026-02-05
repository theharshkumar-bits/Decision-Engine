package com.example.decision_engine.controller;

import com.example.decision_engine.cache.RuleCache;
import com.example.decision_engine.model.EvaluationReportEntity;
import com.example.decision_engine.model.RuleEntity;
import com.example.decision_engine.model.RuleResultEntity;
import com.example.decision_engine.repository.EvaluationReportRepository;
import com.example.decision_engine.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private RuleService ruleService;
    private RuleCache ruleCache;
    private EvaluationReportRepository evaluationReportRepository;

    AdminController(RuleService ruleService, RuleCache ruleCache, EvaluationReportRepository evaluationReportRepository) {
        this.ruleService = ruleService;
        this.ruleCache = ruleCache;
        this.evaluationReportRepository = evaluationReportRepository;

    }

    @GetMapping("/rules")
    public List<RuleEntity> getAllRules() {
        return ruleService.getAllRules();
    }

    @PutMapping("/rules/{id}/enable")
    public RuleEntity enableRule(@PathVariable Long id){
       RuleEntity rule =ruleService.getRuleById(id);
       rule.setActive(true);
       return ruleService.saveRule(rule);

    }
    @PutMapping("/rules/{id}/disable")
    public RuleEntity disableRule(@PathVariable Long id){
        RuleEntity rule =ruleService.getRuleById(id);
        rule.setActive(false);
        return ruleService.saveRule(rule);

    }
    @PostMapping("/cache/refresh")
    public String refreshCache(){
        ruleCache.refresh();
        return "Cache refreshed successfully";
    }

    @GetMapping("/reports")
    public List<EvaluationReportEntity> getReports(){
        return evaluationReportRepository.findAll();
    }


}

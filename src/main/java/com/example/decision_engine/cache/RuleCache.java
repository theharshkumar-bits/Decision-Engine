package com.example.decision_engine.cache;

import com.example.decision_engine.model.RuleEntity;
import com.example.decision_engine.repository.RuleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RuleCache {
    private List<RuleEntity> cache;
    private RuleRepository ruleRepository;
    RuleCache(RuleRepository ruleRepository){
        this.ruleRepository=ruleRepository;
    }

    @PostConstruct   //Load Rules at startup
    private void setCache(){
        cache = ruleRepository.findAll();
        System.out.println("rule cached : "+cache.size());
    }

    public void refresh(){
        cache=ruleRepository.findAll();
    }
    public List<RuleEntity> getCache(){
        return cache;
    }
}

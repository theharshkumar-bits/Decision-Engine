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
//        cache = ruleRepository.findAll();
//        System.out.println("rule cached : "+cache.size());
        refresh();
    }

//    public void refresh(){
        //after adding CacheController , i added synchronized keyword to avoid thread condition to avoid unpredictable result ex only 5 result updated or 10 rule updated means not fully written list it can be half written list not with complete rules
        //Worst case:
        //One request uses slightly outdated rules (old cache) for a moment.
        //That is acceptable in most caching systems.
        //no need now for ReadWriteLock
        public synchronized void refresh(){
        cache=ruleRepository.findAll();
        System.out.println("Rule cache refresh and size = "+cache.size());
    }
    public List<RuleEntity> getCache(){
        return cache;
    }
}

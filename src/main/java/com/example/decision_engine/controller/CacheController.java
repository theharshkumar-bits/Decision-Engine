package com.example.decision_engine.controller;

import com.example.decision_engine.cache.RuleCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class CacheController {
    private RuleCache ruleCache;

    CacheController(RuleCache ruleCache) {
        this.ruleCache = ruleCache;
    }

    //for manual Cache refresh
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshCache(){
        ruleCache.refresh();
        return ResponseEntity.ok("Rule Cache Refreshed Successfully");  //for giving response with message
    }
}

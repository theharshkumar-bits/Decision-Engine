package com.example.decision_engine.controller;


import com.example.decision_engine.model.RuleEntity;
import com.example.decision_engine.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// combines controller and response body combines //@Controller @ResponseBody  means return JSON not HTML

@RequestMapping("/api/rules")
public class RuleController {

    private final RuleService ruleService;

    //Injection   Spring injects RuleService
    RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public RuleEntity createRule(@RequestBody RuleEntity rule) {
        return ruleService.saveRule(rule);
    }
/*What Spring expected
Without @RequestBody, Spring assumes:
Data comes from URL parameters / form data
NOT from JSON body*/

    @GetMapping
    public List<RuleEntity> getAllRules() {
        return ruleService.getAllRules();
    }

    //variable is a part of url
    @GetMapping("/{id}")
    public RuleEntity getElementById(@PathVariable Long id){   //@PathVariable takes variable from url
       return  ruleService.getRuleById(id);
    }
//    @PathVariable is Http method


    //update rule
    @PutMapping("/{id}")
    public RuleEntity updateRule(@PathVariable Long id,@RequestBody RuleEntity rule){
        return ruleService.updateRule(id,rule );
    }

    //Delete Rule
    @DeleteMapping("/{id}")
    public void deleteRule(@PathVariable Long id){
         ruleService.deleteRule(id);   ///Returning void is perfectly fine for DELETE. Spring will respond with:
                                      ///204 No Content (successful delete)
    }


}

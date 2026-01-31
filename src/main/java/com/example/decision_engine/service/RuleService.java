package com.example.decision_engine.service;


import com.example.decision_engine.cache.RuleCache;
import com.example.decision_engine.model.RuleEntity;
import com.example.decision_engine.repository.RuleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Later, I’ll add:
//@ControllerAdvice
//To centralize all exception handling.
//But not now


@Service    //it will tell to spring manage this class   , Spring Creates an object automatically for RulRepository or JpaRepository methods to convert java Object ot sql and sql result to java object
            //without Service spring will not know about this class
public class RuleService {

    private final RuleRepository ruleRepository;    //this is an dependency, Service depends on Repository to talk with DB
    private final RuleCache ruleCache;

    //dependency injection because spring inject object for RuleRepository (already have implemented class) , we don't have to create it
    public RuleService(RuleRepository ruleRepository, RuleCache ruleCache){    //Spring implicitly inject object for this
        this.ruleRepository = ruleRepository;
        this.ruleCache=ruleCache;
    }

//    --------------------->//CRUD OPERATIONS
    /// SaveRule(Post)
    //for saving the rule in database using repository methods
    public RuleEntity saveRule (RuleEntity rule){

        RuleEntity saverule= ruleRepository.save(rule);
        ruleCache.refresh();
        return saverule;
    }

    /// ReadingAllRules
    //for fetching all the rules from rules table
    public List<RuleEntity> getAllRules(){
        return ruleRepository.findAll();
    }


    /// ReadById (Fetch)
    //for fetching rule according to particular id
    public RuleEntity getRuleById(Long id){
                              //findBYId return type is Optional
        return ruleRepository.findById(id).orElseThrow(()->new ResponseStatusException( HttpStatus.NOT_FOUND,"Rule not Found with id :"+id));
                                                           //before that i am throwing here RuntimeException  which does not know about HttpStatus.NOT_FOUND (specific property)so i change to ResponseStatusException
                                                           //“I handled missing resources using proper HTTP semantics instead of generic 500 errors.”

    }


   /// Update
    //for updating rule according to provided id
    public RuleEntity updateRule(Long id, RuleEntity updatedRule){
      RuleEntity existingRule=  ruleRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Rule not Found with id: "+id)); //checking before updating the values
        /// if not present above line of code throw exception and below line of code will not get executed


      //updating now
        existingRule.setName(updatedRule.getName());
        existingRule.setExpression(updatedRule.getExpression());
        existingRule.setWeight(updatedRule.getWeight());
        existingRule.setActive(updatedRule.isActive());


        RuleEntity updaterule= ruleRepository.save(existingRule);  //save perform update not new rule because of existing id
                                                   //no no , no because of id here internally save method will check the id is present or not if it is present then it is update otherwise create new id
                                                   //but here before creating new id(if not present) and throwing error
                                                   //so save perform update here not creating new rule here
        ruleCache.refresh();
        return updaterule;
    }



    //delete rule
    public void deleteRule(Long id){
        RuleEntity rule=  ruleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"rule not Found with id "+id));

        ruleRepository.delete(rule);
        ruleCache.refresh();
//        ruleRepository.deleteById(id);  we can use it also because if id not present Exception occurred and below line of code will not execute

    }

}

package com.example.decision_engine.engine;

//Later (not now), we will:
//Add try-catch for invalid expressions
//Add rule priority
//Add caching

import com.example.decision_engine.cache.RuleCache;
import com.example.decision_engine.dto.DecisionReport;
import com.example.decision_engine.dto.RuleResult;
import com.example.decision_engine.model.Candidate;
import com.example.decision_engine.model.EvaluationReportEntity;
import com.example.decision_engine.model.RuleEntity;
import com.example.decision_engine.model.RuleResultEntity;
import com.example.decision_engine.repository.EvaluationReportRepository;
import com.example.decision_engine.repository.RuleRepository;
//import org.springframework.expression.*;
//import org.springframework.expression.spel;
//Or
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/// Main BRAIN

@Component  //Create ONE object of this class and manage it for me.
public class RuleEvaluationEngine {
//    private RuleRepository  ruleRepository;

    private RuleCache ruleCache;

    /// SpEl parser  (why we are not creating this parser inside the method)
    ExpressionParser parser = new SpelExpressionParser();  //for one Object we create this outside of loop

    /// for caching mechanism
//    private List<RuleEntity> cachedRules = new ArrayList<>();
    private List<RuleEntity> cachedRules;

    //dependency injection

//    RuleEvaluationEngine(RuleRepository ruleRepository){
//        this.ruleRepository =ruleRepository;
//    }

    //for finalEvaluationReport Dabase
    private final EvaluationReportRepository reportRepository;

    //for cache
    RuleEvaluationEngine(RuleCache ruleCache, EvaluationReportRepository reportRepository) {
        this.ruleCache = ruleCache;
        this.reportRepository= reportRepository;
    }

//    @PostConstruct    // means this method will run only once after object creation of RuleEvaluationEngine class
//    private void loadRulesIntoCache(){
//        cachedRules = ruleRepository.findAll();
//        System.out.println("all rules Cached : "+cachedRules.size());
//    }

    public DecisionReport evaluate(Candidate candidate) {

        //every evaluation i am hitting the db not good for large ex 10k/sec request at a time
//        List<RuleEntity> rules = ruleRepository.findAll();

        /// Score Tracking
        List<RuleResult> breakdown = new ArrayList<>(); //we can directly create here DecisionReport and for each result we can add using .add(result) but for using DecisionReport setter method we create extra data-structure
        int totalScore = 0;

        List<RuleEntity> rules = new ArrayList<>(ruleCache.getCache()); //for creating own copy so that sorting for this object will not effect to others in future
        cachedRules = ruleCache.getCache();
        //for evaluating according to priority
//        cachedRules.sort((r1, r2) -> Integer.compare(r1.getPriority(), r2.getPriority()));
        rules.sort((r1, r2) -> r1.getPriority().compareTo(r2.getPriority()));   //Priority 1 runs first


        for (RuleEntity rule : rules) {   //now iterating in own copy of rules with priority

            //for status
            if (!rule.isActive()) continue;


            //for versioning - skips rules not valid at current time.
            LocalDateTime now = LocalDateTime.now();
            if(rule.getEffectiveFrom() !=null && now.isBefore(rule.getEffectiveFrom())) continue;
            if(rule.getEffectiveTo() !=null && now.isAfter(rule.getEffectiveTo())) continue;

            RuleResult result = new RuleResult();
            result.setRuleName(rule.getName());

            result.setExpression(rule.getExpression());

            //try block for stoping crashing of application due to wrong expression now if any rule failed that will be skipped effectively and application will not stop
            try {
                /// Evaluation Context
                //preparing for SpEl expression (#candidate.expression>10? 5:3;)
                StandardEvaluationContext context = new StandardEvaluationContext();
                context.setVariable("candidate", candidate);   //when you see candidate, use THIS Java object

                //using parsing  //after parsing i will get AST(Abstract Syntax tree)
                Expression expression = parser.parseExpression(rule.getExpression());   //getting some value after parsing
                //expression execution
                Integer score = expression.getValue(context, Integer.class);
                //Integer.class - >The Class object that represents Integer type

//                totalScore += score;          //not affected in case of error occur due to wrong expression

//                result.setRuleName(rule.getName());

                //now calculating score including with weight
                int weight = rule.getWeight();
                int weightedScore = score*weight;
                totalScore+= weightedScore;

                result.setScore(score);
                result.setWeight(weight);
                result.setWeightedScore(weightedScore);
//                result.setExpression(rule.getExpression());
                result.setErrorMessage("No Error");
                result.setStatus("SUCCESS");

            } catch (Exception e) {
                result.setScore(0);
                result.setStatus("FAILED");
                result.setErrorMessage(e.getMessage());
                System.out.println("Rule Failed "+ rule.getName());
            }

            breakdown.add(result);


        }

        DecisionReport report = new DecisionReport();
        report.setFinalScore(totalScore);
        report.setBreakdown(breakdown);

        //for decision
        String decision;

        if (totalScore >= 100) {
            decision = "ACCEPT";
        } else if (totalScore >= 60) {
            decision = "HOLD";
        } else {
            decision = "REJECT";
        }
        report.setDecision(decision);
//=============================================
        /// creating evaluation report for storing in database

        EvaluationReportEntity entity = new EvaluationReportEntity();
        entity.setFinalScore(totalScore);
        entity.setCreatedAt(LocalDateTime.now());

        List<RuleResultEntity> ruleResultEntities = new ArrayList<>();
        for(RuleResult r : breakdown){
            RuleResultEntity re = new RuleResultEntity();
            re.setRuleName(r.getRuleName());
            re.setScore(r.getScore());
            re.setExpression(r.getExpression());
            re.setStatus(r.getStatus());
            re.setErrorMessage(r.getErrorMessage());
            re.setReport(entity);
            ruleResultEntities.add(re);
        }
        entity.setResults(ruleResultEntities);
        reportRepository.save(entity);
 //==========================================
        return report;

    }
}

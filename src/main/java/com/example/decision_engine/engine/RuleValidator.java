package com.example.decision_engine.engine;

import com.example.decision_engine.model.Candidate;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;


@Component
public class RuleValidator {
    ExpressionParser parser = new SpelExpressionParser();

    public void validate(String expressionText) {
        try {
        //just for validation
        Candidate candidate = new Candidate();
        candidate.setExperience(5);
        candidate.setSkillCount(3);


            StandardEvaluationContext context = new StandardEvaluationContext();

            context.setVariable("candidate", candidate);

            Expression expression = parser.parseExpression(expressionText);
            expression.getValue(context, Integer.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid SpEl Expression : "+e.getMessage());
        }
    }
}

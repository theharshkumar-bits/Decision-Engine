package com.example.decision_engine.repository;

import com.example.decision_engine.model.EvaluationReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationReportRepository  extends JpaRepository<EvaluationReportEntity, Long> {

}
////no need for RuleResultEntity because cascade will save it
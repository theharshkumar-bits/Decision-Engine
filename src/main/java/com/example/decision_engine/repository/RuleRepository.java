package com.example.decision_engine.repository;

import com.example.decision_engine.model.RuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Gives DB access (save, find, delete)
//No SQL required
//Used by Service layer

public interface RuleRepository extends JpaRepository<RuleEntity,Long> {
//    List<RuleEntity> findByActionTrue();
}

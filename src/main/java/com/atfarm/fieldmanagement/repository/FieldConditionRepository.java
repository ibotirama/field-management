package com.atfarm.fieldmanagement.repository;

import com.atfarm.fieldmanagement.model.FieldCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldConditionRepository extends CrudRepository<FieldCondition, Long> {
}

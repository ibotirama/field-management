package com.atfarm.fieldmanagement.repository;

import com.atfarm.fieldmanagement.model.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends CrudRepository<Field, Long> {
}

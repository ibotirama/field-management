package com.atfarm.fieldmanagement.resource;

import com.atfarm.fieldmanagement.model.FieldCondition;
import com.atfarm.fieldmanagement.repository.FieldConditionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/fieldconditions")
public class FieldConditionResource {
    @Autowired
    private FieldConditionRepository fieldConditions;

    @GetMapping
    public Iterable<FieldCondition> findAll(){
        return this.fieldConditions.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<FieldCondition> fieldCondition = this.fieldConditions.findById(id);
        if (!fieldCondition.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(fieldCondition.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FieldCondition create(@Valid @RequestBody FieldCondition fieldCondition){
        return this.fieldConditions.save(fieldCondition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldCondition> update(@PathVariable Long id, @Valid @RequestBody FieldCondition fieldCondition){
        Optional<FieldCondition> fieldConditionsOpt = this.fieldConditions.findById(id);
        if (fieldConditionsOpt.isPresent()){
            FieldCondition fieldConditionDb = fieldConditionsOpt.get();
            BeanUtils.copyProperties(fieldCondition, fieldConditionDb, "id");
            fieldConditionDb = this.fieldConditions.save(fieldConditionDb);
            return ResponseEntity.ok(fieldConditionDb);
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<FieldCondition> fieldConditionOpt = fieldConditions.findById(id);
        if (!fieldConditionOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fieldConditionOpt.get());
    }

}

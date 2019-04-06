package com.atfarm.fieldmanagement.resource;

import com.atfarm.fieldmanagement.model.Field;
import com.atfarm.fieldmanagement.repository.FieldRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/fields")
public class FieldResource {
    @Autowired
    private FieldRepository fields;

    @GetMapping
    public Iterable<Field> findAll(){
        return this.fields.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Field> fieldOpt = this.fields.findById(id);
        if (!fieldOpt.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(fieldOpt.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Field create(@Valid @RequestBody Field field){
        return this.fields.save(field);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> update(@PathVariable Long id, @Valid @RequestBody Field field){
        Optional<Field> fieldOpt = this.fields.findById(id);
        if (fieldOpt.isPresent()){
            Field fieldDb = fieldOpt.get();
            BeanUtils.copyProperties(field, fieldDb, "id");
            fieldDb = this.fields.save(fieldDb);
            return ResponseEntity.ok(fieldDb);
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Field> fieldOpt = fields.findById(id);
        if (!fieldOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fieldOpt.get());
    }

}

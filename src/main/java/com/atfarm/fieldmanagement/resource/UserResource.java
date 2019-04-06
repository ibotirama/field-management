package com.atfarm.fieldmanagement.resource;

import com.atfarm.fieldmanagement.model.User;
import com.atfarm.fieldmanagement.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    private UserRepository users;

    @GetMapping
    public Iterable<User> findAll(){
        return this.users.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> userOpt = this.users.findById(id);
        if (!userOpt.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userOpt.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user){
        return this.users.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user){
        Optional<User> userOpt = this.users.findById(id);
        if (userOpt.isPresent()){
            User userDb = userOpt.get();
            BeanUtils.copyProperties(user, userDb, "id");
            userDb = this.users.save(userDb);
            return ResponseEntity.ok(userDb);
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOpt = users.findById(id);
        if (!userOpt.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userOpt.get());
    }
}

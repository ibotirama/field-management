package com.atfarm.fieldmanagement.repository;

import com.atfarm.fieldmanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
}

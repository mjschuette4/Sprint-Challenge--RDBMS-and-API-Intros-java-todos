package com.sprintchallenge.demo.repositories;

import com.sprintchallenge.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

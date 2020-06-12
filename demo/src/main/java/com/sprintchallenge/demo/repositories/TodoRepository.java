package com.sprintchallenge.demo.repositories;

import com.sprintchallenge.demo.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}

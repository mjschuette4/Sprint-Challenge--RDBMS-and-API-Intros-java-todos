package com.sprintchallenge.demo.services;

import com.sprintchallenge.demo.models.Todo;

public interface TodoService {
    Todo getTodoById(long id);
    Todo updateTodo(Todo todo, long id);
    Todo save(Todo todo, long userid);
}

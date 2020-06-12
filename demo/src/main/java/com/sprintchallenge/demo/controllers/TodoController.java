package com.sprintchallenge.demo.controllers;

import com.sprintchallenge.demo.models.Todo;
import com.sprintchallenge.demo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TodoController {
    @Autowired
    TodoService todoService;

    //PUT http://localhost:2019/todos/tod0/{todoid}
    @PutMapping(value = "/todo/{todoid}", consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo updateTodo, @PathVariable long todoid){

        todoService.updateTodo(updateTodo, todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

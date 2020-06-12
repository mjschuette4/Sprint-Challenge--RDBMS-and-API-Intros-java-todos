package com.sprintchallenge.demo.controllers;

import com.sprintchallenge.demo.models.Todo;
import com.sprintchallenge.demo.models.User;
import com.sprintchallenge.demo.services.TodoService;
import com.sprintchallenge.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    //Get http://localhost:2019/users/users
    @GetMapping(value = "users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userService.getAllUsers();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    //Get http://localhost:2019/users/user/{userid}
    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> findUserById(@PathVariable long userid) {//join table to find user tod0?
        User myUser = userService.getUserById(userid);
        return new ResponseEntity<>(myUser, HttpStatus.OK);
    }

    //Post http://localhost:2019/users/user
    @PostMapping(value = "/user", consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser) {
        newUser = userService.save(newUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("userid")
                .buildAndExpand(newUser.getUserid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

        //DELETE http://localhost:2019/users/userid/{userid}
    @DeleteMapping(value = "/userid/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid) {
        userService.delete(userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/users/todo{userid}
    @PostMapping(value = "/todo/{userid}", consumes = {"application/json"})
    public ResponseEntity<?> addTodoToUser(@Valid @RequestBody Todo myTodo, @PathVariable long userid) {
        myTodo = todoService.save(myTodo, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

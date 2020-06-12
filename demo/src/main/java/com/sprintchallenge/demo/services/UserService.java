package com.sprintchallenge.demo.services;

import com.sprintchallenge.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);

    User save(User user);
    User update (User user, long id );
    void delete(long id);
}

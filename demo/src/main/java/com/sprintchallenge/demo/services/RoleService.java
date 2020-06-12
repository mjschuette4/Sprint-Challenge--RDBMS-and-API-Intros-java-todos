package com.sprintchallenge.demo.services;

import com.sprintchallenge.demo.models.Role;

public interface RoleService {
    Role getRoleById(long id);
    Role save(Role role);
}

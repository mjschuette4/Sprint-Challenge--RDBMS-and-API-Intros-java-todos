package com.sprintchallenge.demo.repositories;

import com.sprintchallenge.demo.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}

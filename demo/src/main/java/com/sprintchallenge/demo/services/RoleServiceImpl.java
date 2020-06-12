package com.sprintchallenge.demo.services;

import com.sprintchallenge.demo.models.Role;
import com.sprintchallenge.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role getRoleById(long id) {
        return roleRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Role " + id + " not found"));
    }

    @Override
    public Role save(Role role) {
        Role newRole = new Role();
        newRole.setUsers(new ArrayList<>());
        newRole.setRolename(role.getRolename());
        return roleRepo.save(newRole);
    }
}

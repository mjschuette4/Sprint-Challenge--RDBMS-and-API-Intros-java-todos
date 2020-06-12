package com.sprintchallenge.demo.services;

import com.sprintchallenge.demo.models.Role;
import com.sprintchallenge.demo.models.Todo;
import com.sprintchallenge.demo.models.User;
import com.sprintchallenge.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userrepos;
    @Autowired
    private RoleService roleService;

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);//comes back as iterator then converts to arraylist
        return list;
    }

    @Override
    public User getUserById(long id) {
        return userrepos.findById(id).orElseThrow(()-> new EntityNotFoundException("User " + id + " not found"));
    }

    @Override
    public User save(User user) {
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());

        for (Todo t: user.getTodos() ){
            Todo newTodo = new Todo(t.getDescription(),t.getDatestarted(), t.completedSwitch, newUser);
            newUser.getTodos().add(newTodo);
        }

        //loops through and adds the role
        for (Role r : user.getRoles()){
            Role newRole = roleService.getRoleById(r.getRoleid());
            newUser.addRole(newRole);
        }
        //Returns new user
        return userrepos.save(newUser);
    }

    @Override
    public User update(User user, long id) {
        User updateUser = new User();

        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setPrimaryemail(user.getPrimaryemail());

        for (Todo t: user.getTodos() ){
            Todo updateTodo = new Todo(t.getDescription(),t.getDatestarted(), t.completedSwitch, updateUser);
            updateUser.getTodos().add(updateTodo);
        }

        for (Role r : user.getRoles()){
            Role updateRole = roleService.getRoleById(r.getRoleid());
            updateUser.addRole(updateRole);
        }
        return null;
    }

    //this will either delete completely or not at all
    @Transactional
    @Override
    public void delete(long id) {
        if(getUserById(id) != null){
            userrepos.deleteById(id);
        }

    }
}

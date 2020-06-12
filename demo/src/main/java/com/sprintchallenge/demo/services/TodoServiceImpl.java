package com.sprintchallenge.demo.services;

import com.sprintchallenge.demo.models.Todo;
import com.sprintchallenge.demo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{
    @Autowired
    TodoRepository todorepos;
    @Autowired
    UserService userService;

    @Override
    public Todo getTodoById(long id) {
        return todorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + id + " not found"));
    }

    @Transactional
    @Override
    public Todo updateTodo(Todo todo, long id) {
        Todo updateTodo = getTodoById(id);

        if(todo.getDatestarted()!=null){
            updateTodo.setDatestarted(todo.getDatestarted());
        }
        if (todo.getDescription() != null) {
            updateTodo.setDescription(todo.getDescription());
        }
        if (todo.getUser() != null) {
            updateTodo.setUser(userService.getUserById(todo.getUser().getUserid()));
        }

        if (todo.completedSwitch) {
            updateTodo.setCompleted(todo.isCompletedSwitch());
        }

        return todorepos.save(updateTodo);
    }

    @Override
    public Todo save(Todo todo, long userid) {
        Todo newTodo = new Todo();
        newTodo.setDescription(todo.getDescription());//set get like John says
        newTodo.setUser(todo.getUser());
        newTodo.setDatestarted(todo.getDatestarted());

        return todorepos.save(newTodo);
    }
}

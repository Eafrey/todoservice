package com.thoughtworks.traing.chensen.todoservice.service;

import com.thoughtworks.traing.chensen.todoservice.model.Todo;
import com.thoughtworks.traing.chensen.todoservice.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;


    public List<Todo> getToDos() throws IOException {
//        SecurityContextHolder.getContext();
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return toDoRepository.findTodoInfosByCreateByIs(id);
    }

    @Transactional
    public Todo find(Integer id) {
        return Optional.ofNullable(toDoRepository.findOne(id))
                .orElseThrow(null);
    }

    public void delete(Integer id) {
        toDoRepository.delete(id);
    }

    public void update(Todo todo) {
        toDoRepository.save(todo);
    }

    public void add(Todo todo) {
        int id = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        todo.setCreateBy(id);
        toDoRepository.save(todo);
    }
}

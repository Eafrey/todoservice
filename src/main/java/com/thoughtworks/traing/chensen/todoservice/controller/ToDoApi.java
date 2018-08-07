package com.thoughtworks.traing.chensen.todoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.traing.chensen.todoservice.model.Todo;
import com.thoughtworks.traing.chensen.todoservice.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ToDoApi {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private ObjectMapper objectMapper;


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void onNotFoundException() {

    }

    @GetMapping("/todos/{id}")
    public Todo find(@PathVariable Integer id) {
        return toDoService.find(id);
    }

    @PostMapping("/todos")
    public void addToDo(@RequestBody Todo todo) {
        toDoService.add(todo);
    }


    @DeleteMapping("/todos/{id}")
    public void updateToDo(@PathVariable Integer id) {
        toDoService.delete(id);
    }

    @GetMapping("/todos")
    public List<Todo> todo() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println("toDoService.getToDos()" + toDoService.getToDos());
//        List<Todo> list = objectMapper.readValue(toDoService.getToDos(), new TypeReference<List<Todo>>(){});
        return toDoService.getToDos();
    }
}

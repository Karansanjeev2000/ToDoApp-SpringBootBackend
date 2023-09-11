package com.example.TodoService.Service;

import com.example.TodoService.Domain.Todo;
import com.example.TodoService.Domain.User;
import com.example.TodoService.Exception.TaskAlreadyExistsException;
import com.example.TodoService.Exception.TaskNotFoundException;
import com.example.TodoService.Exception.UserAlreadyExistsException;
import com.example.TodoService.Exception.UserNotFoundException;

import java.util.List;

public interface todoService {
    public User registerUser(User obj) throws  UserAlreadyExistsException;
    public List<Todo> getalltasks(String email);
    public String DeleteByTaskId(String email,String id)throws UserNotFoundException,TaskNotFoundException;
    public Todo updateTask(String email,Todo obj)throws UserNotFoundException;
    public User saveTask(String email,Todo obj)throws UserNotFoundException,TaskAlreadyExistsException;
    public Todo getTaskById(String email,String id) throws UserNotFoundException,TaskNotFoundException;

}

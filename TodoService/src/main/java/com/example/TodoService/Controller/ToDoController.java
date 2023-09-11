package com.example.TodoService.Controller;

import com.example.TodoService.Domain.Todo;
import com.example.TodoService.Domain.User;
import com.example.TodoService.Exception.TaskAlreadyExistsException;
import com.example.TodoService.Exception.TaskNotFoundException;
import com.example.TodoService.Exception.UserAlreadyExistsException;
import com.example.TodoService.Exception.UserNotFoundException;
import com.example.TodoService.Service.todoServiceImpl;
import io.jsonwebtoken.Claims;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api/v2")
public class ToDoController {
    private todoServiceImpl todoService;
    private ResponseEntity responseEntity;
    @Autowired
    ToDoController(todoServiceImpl todoService){
        this.todoService=todoService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        // Register a new user and save to db, return 201 status if user is saved else 500 status
        try {
            User create =todoService.registerUser(user);
            return new ResponseEntity<>(create, HttpStatus.CREATED);
        }catch (UserAlreadyExistsException u){
            throw new UserAlreadyExistsException();
        }

    }
    @PostMapping("/user/saveTask")
    public ResponseEntity<?> saveTask(@RequestBody Todo obj, HttpServletRequest request) throws UserNotFoundException, TaskAlreadyExistsException {
        try {
            request.getHeader("Authorization");
            Claims c = (Claims) request.getAttribute("userEmail");
            String email = c.getSubject();
                responseEntity = new ResponseEntity<>(todoService.saveTask(email,obj), HttpStatus.CREATED);
            }
        catch(UserNotFoundException u){
            throw new UserNotFoundException();
        }

        return responseEntity;
    }

    @GetMapping("/user/getall")
    public ResponseEntity<?> getallTask(HttpServletRequest request)throws UserNotFoundException{
        try{
            request.getHeader("Authorization");
            Claims c=(Claims)request.getAttribute("userEmail");
            String email=c.getSubject();
            responseEntity=new ResponseEntity<>(todoService.getalltasks(email),HttpStatus.OK);
        }catch (Exception u){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @PutMapping("/user/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody Todo obj, HttpServletRequest request) throws UserNotFoundException {
        try {
            request.getHeader("Authorization");
            Claims c = (Claims) request.getAttribute("userEmail");
            String email = c.getSubject();
            responseEntity = new ResponseEntity<>(todoService.updateTask(email,obj), HttpStatus.OK);
        } catch (UserNotFoundException u) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @DeleteMapping("/user/task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId,HttpServletRequest request) throws TaskNotFoundException {

        try{
            request.getHeader("Authorization");
            Claims c=(Claims) request.getAttribute("userEmail");
            String email=c.getSubject();
            responseEntity=new ResponseEntity<>(todoService.DeleteByTaskId(email,taskId),HttpStatus.OK);
        } catch (TaskNotFoundException | UserNotFoundException e) {
            throw new TaskNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/user/getTask/{taskId}")
    public ResponseEntity<?> GetTaskByid(@PathVariable String taskId,HttpServletRequest request) throws TaskNotFoundException {

        try{
            request.getHeader("Authorization");
            Claims c=(Claims) request.getAttribute("userEmail");
            String email=c.getSubject();
            responseEntity=new ResponseEntity<>(todoService.getTaskById(email,taskId),HttpStatus.OK);
        } catch (TaskNotFoundException | UserNotFoundException e) {
            throw new TaskNotFoundException();
        }
        return responseEntity;
    }



}

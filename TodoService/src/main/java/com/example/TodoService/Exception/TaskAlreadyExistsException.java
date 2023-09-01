package com.example.TodoService.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use the@ResponseStatus annotation to set the exception message and status
@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Task Already Exist")

public class TaskAlreadyExistsException extends Exception {
}

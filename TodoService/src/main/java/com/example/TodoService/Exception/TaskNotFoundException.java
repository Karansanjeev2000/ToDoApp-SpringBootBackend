package com.example.TodoService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use the@ResponseStatus annotation to set the exception message and status
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Task  Not Found")
public class TaskNotFoundException extends Exception {
}

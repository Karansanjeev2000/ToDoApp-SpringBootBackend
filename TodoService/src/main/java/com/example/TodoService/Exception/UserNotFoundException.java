package com.example.TodoService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User  Not Found")
// Use the@ResponseStatus annotation to set the exception message and status
public class UserNotFoundException extends  Exception{
}

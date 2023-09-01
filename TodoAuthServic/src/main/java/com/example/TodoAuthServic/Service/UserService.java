package com.example.TodoAuthServic.Service;

import com.example.TodoAuthServic.Domain.User;
import com.example.TodoAuthServic.exception.InvalidCredentialsException;
import com.example.TodoAuthServic.exception.UserAlreadyExistsException;

public interface UserService {
    User getByUserEmailAndUserPassword(String useremail,String password) throws InvalidCredentialsException;
    User saveUser(User obj)throws UserAlreadyExistsException;
}

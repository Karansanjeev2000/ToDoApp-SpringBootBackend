package com.example.TodoAuthServic.security;


import com.example.TodoAuthServic.Domain.User;

public interface SecurityTokenGenerator {
    String createToken(User user);
}

package com.example.TodoAuthServic.security;


import com.example.TodoAuthServic.Domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> createToken(User user);
}

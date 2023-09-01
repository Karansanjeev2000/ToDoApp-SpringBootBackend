package com.example.TodoService.Proxy;

import com.example.TodoService.Domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="To-do-Auth-Servic",url="localhost:8085")
public interface UserProxy {
    @PostMapping("/api/v1/register")
    public ResponseEntity<?> SaveUser(@RequestBody User Obj);

}




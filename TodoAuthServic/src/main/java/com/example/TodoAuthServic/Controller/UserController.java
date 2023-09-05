package com.example.TodoAuthServic.Controller;

import com.example.TodoAuthServic.Domain.User;
import com.example.TodoAuthServic.Service.UserService;
import com.example.TodoAuthServic.Service.UserServiceImpl;
import com.example.TodoAuthServic.exception.InvalidCredentialsException;
import com.example.TodoAuthServic.exception.UserAlreadyExistsException;
import com.example.TodoAuthServic.security.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class UserController {
    UserServiceImpl userService;

    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    UserController(UserServiceImpl userService,SecurityTokenGenerator securityTokenGenerator){
        this.userService=userService;
        this.securityTokenGenerator=securityTokenGenerator;
    }
    @PostMapping("/login")
    public ResponseEntity<?> Userlogin(@RequestBody User obj)throws InvalidCredentialsException{
        User user=userService.getByUserEmailAndUserPassword(obj.getUserEmail(), obj.getUserPassword());
        if(user==null){
            throw new  InvalidCredentialsException();
        }
        Map<String,String> token=securityTokenGenerator.createToken(user);

        return  new ResponseEntity<>(token, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> SaveUser(@RequestBody User Obj) throws UserAlreadyExistsException{
         try{
             userService.saveUser(Obj);
         }
         catch (UserAlreadyExistsException ae){
             throw  new UserAlreadyExistsException();
         }

         return new ResponseEntity<>(Obj,HttpStatus.CREATED);

    }

}

package com.example.TodoAuthServic.Service;

import com.example.TodoAuthServic.Domain.User;
import com.example.TodoAuthServic.Repository.UserRepository;
import com.example.TodoAuthServic.exception.InvalidCredentialsException;
import com.example.TodoAuthServic.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Autowired
    UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    @Override
    public User getByUserEmailAndUserPassword(String useremail, String password) throws InvalidCredentialsException {
    User obj=userRepository.findByUserEmailAndUserPassword(useremail,password);
    if(obj==null){
        throw new InvalidCredentialsException();
    }
        return  obj;

    }

    @Override
    public User saveUser(User obj)throws UserAlreadyExistsException {

       if(userRepository.findById(obj.getUserEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        userRepository.save(obj);
        return obj;
    }
}

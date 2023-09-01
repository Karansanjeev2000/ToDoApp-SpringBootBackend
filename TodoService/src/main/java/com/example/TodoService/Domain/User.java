package com.example.TodoService.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {
    @Id
    String userEmail;
    String userPassword;
    String userPhone;
    String userName;
    List<Todo> listOfTodo;

    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userName='" + userName + '\'' +
                ", listOfTodo=" + listOfTodo +
                '}';
    }

    public User() {
    }

    public User(String userEmail, String userPassword, String userPhone, String userName, List<Todo> listOfTodo) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userName = userName;
        this.listOfTodo = listOfTodo;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Todo> getListOfTodo() {
        return listOfTodo;
    }

    public void setListOfTodo(List<Todo> listOfTodo) {
        this.listOfTodo = listOfTodo;
    }
}

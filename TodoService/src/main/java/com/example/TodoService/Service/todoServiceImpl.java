package com.example.TodoService.Service;

import com.example.TodoService.Domain.Todo;
import com.example.TodoService.Domain.User;
import com.example.TodoService.Exception.TaskAlreadyExistsException;
import com.example.TodoService.Exception.TaskNotFoundException;
import com.example.TodoService.Exception.UserAlreadyExistsException;
import com.example.TodoService.Exception.UserNotFoundException;
import com.example.TodoService.Proxy.UserProxy;
import com.example.TodoService.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class todoServiceImpl implements todoService{
     private UserProxy userProxy;  private  TodoRepository todoRepository;

    @Autowired
    public todoServiceImpl(UserProxy userProxy,TodoRepository todoRepository){
        this.todoRepository=todoRepository;
        this.userProxy = userProxy;

    }

    @Override
    public User registerUser(User obj) throws UserAlreadyExistsException {

        if(todoRepository.findById(obj.getUserEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user=todoRepository.save(obj);

        if(user.getUserEmail()!=null){
            userProxy.SaveUser(obj);
        }
        return todoRepository.findById(obj.getUserEmail()).get();
    }
    @Override
    public List<Todo> getalltasks(String email) {
        User obj=todoRepository.findById(email).get();
        return obj.getListOfTodo();

    }


    @Override
    public String DeleteByTaskId(String email,String id)throws UserNotFoundException, TaskNotFoundException {
        if(todoRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User object=todoRepository.findById(email).get();
        List<Todo> Tlist=object.getListOfTodo();
        if(Tlist.size()==0 || Tlist==null){
            throw  new TaskNotFoundException();
        }

        for(var i=0;i<Tlist.size();i++){
            if(Tlist.get(i).getTaskid().equals(id)){
                Tlist.remove(i); /// removing based on index
                todoRepository.save(object); // updating the removed list
                return "Successfully deleted";
            }
        }
        return  "Deletion failed";
    }

    @Override
    public Todo updateTask(String email,Todo obj) throws UserNotFoundException {

        User isTobeUpdatedUser = todoRepository.findById(email).get();
        System.out.println(isTobeUpdatedUser);
        User existingUser = null;
        List<Todo>  existingTasks = new ArrayList<>();
        if (todoRepository.findById(email).isPresent()) {
            existingUser = todoRepository.findById(email).get();
            existingTasks=existingUser.getListOfTodo();

            String TaskId = obj.getTaskid();


            for (int i = 0; i < existingTasks.size(); i++) {
                if (existingTasks.get(i).getTaskid().equals(obj.getTaskid())) {

                    if (obj.getCategory() != null) {
                        existingTasks.get(i).setCategory(obj.getCategory());
                    }

                    if ( obj.getDueDate()!=null) {
                        existingTasks.get(i).setDueDate(obj.getDueDate());
                    }
                    if (obj.getImgSrc()!= null) {
                        existingTasks.get(i).setImgSrc(obj.getImgSrc());
                    }
                    if (obj.getPriority()!= null) {
                        existingTasks.get(i).setPriority(obj.getPriority());
                    }
                    if (obj.getStatus()!= null) {
                        existingTasks.get(i).setStatus(obj.getStatus());
                    }
                    if (obj.getTaskName()!= null) {
                        existingTasks.get(i).setTaskName(obj.getTaskName());
                    }
                    if (obj.getTaskDesc()!= null) {
                        existingTasks.get(i).setTaskDesc(obj.getTaskDesc());
                    }

                }
            }
             todoRepository.save(existingUser);

        }else{
            throw new UserNotFoundException();
        }
        return  obj;
    }


    @Override
    public User saveTask(String email,Todo obj) throws UserNotFoundException,TaskAlreadyExistsException {
        User user=todoRepository.findById(email).get();

        if(todoRepository.findById(email).isEmpty()){
           throw new UserNotFoundException();
        }

        if (user.getListOfTodo() == null){
            user.setListOfTodo(Arrays.asList(obj));
        }
        else{
            for (int i = 0; i < user.getListOfTodo().size(); i++) {

                if (user.getListOfTodo().get(i).getTaskName().equals(obj.getTaskName())){
                    throw new TaskAlreadyExistsException();
                }
            }
            List<Todo> taskList = user.getListOfTodo();
            taskList.add(obj);
            user.setListOfTodo(taskList);

        }
        todoRepository.save(user);
        return user;
    }

    @Override
    public Todo getTaskById(String email, String id) throws UserNotFoundException,TaskNotFoundException {
        if(todoRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User object=todoRepository.findById(email).get();
        List<Todo> Tlist=object.getListOfTodo();
        if(Tlist.size()==0 || Tlist==null){
            throw  new TaskNotFoundException();
        }

        for(var i=0;i<Tlist.size();i++){
            if(Tlist.get(i).getTaskid().equals(id)){
                return Tlist.get(i);
            }
        }
        return  null;
    }



}

package com.example.TodoAuthServic.Repository;

import com.example.TodoAuthServic.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);

}

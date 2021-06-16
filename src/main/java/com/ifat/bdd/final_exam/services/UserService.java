package com.ifat.bdd.final_exam.services;

import java.util.List;

import com.ifat.bdd.final_exam.model.loading.User;
import com.ifat.bdd.final_exam.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


}

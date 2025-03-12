package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public String handleHello(){
        return "hello from service";
    }

    public User saveUser(User name){
        User client=this.userRepository.save(name);
        return client;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    
    public List<User> getUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User findUserById(long n){   
        User user=this.userRepository.findTop1ById(n);
        return user;
    }

    public void deleteAUser(long n){
        this.userRepository.deleteById(n);
    }
}

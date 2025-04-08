package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDto;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
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

    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }

    public User changeRegisterDtoToUser(RegisterDto res){
        User user=new User();
        user.setFullName(res.getFirstName()+" "+res.getLastName());
        user.setEmail(res.getEmail());
        user.setPassword(res.getPassword());
        return user;
    }

    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail1(String email){
        return this.userRepository.findTop1ByEmail(email);
    }

    public User getUserByPasswordToken(String token){
        return this.userRepository.findByResetPasswordToken(token);
    }

}

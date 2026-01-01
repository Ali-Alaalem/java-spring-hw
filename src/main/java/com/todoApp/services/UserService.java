package com.todoApp.services;

import com.todoApp.exceptions.InformationExistException;
import com.todoApp.models.User;
import com.todoApp.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User objectUser){
        if(!userRepository.existsByEmail(objectUser.getEmail())){
objectUser.setPassword(passwordEncoder.encode(objectUser.getPassword()));
            return userRepository.save(objectUser);
        }else{
            throw new InformationExistException("User with email address " +objectUser.getEmail() + "already exist");
        }
    }
}

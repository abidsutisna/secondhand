package com.secondhand.secondhand.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondhand.secondhand.models.entities.User;
import com.secondhand.secondhand.models.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User newUser) {
        return this.userRepository.save(newUser);
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.findById(user.getUserId()).orElseThrow();
        this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteUserById(id);
        
    }

    @Override
    public User getById(Long userId) {

        return null;
    }

    @Override
    public List<User> getAllUser() {

        return null;
    }
    
}

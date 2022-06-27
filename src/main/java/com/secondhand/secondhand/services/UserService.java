package com.secondhand.secondhand.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.secondhand.secondhand.models.entities.User;
public interface UserService {
    
    public User addUser(User newUser);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getById(Long userId);
    List<User> getAllUser();
    public UserDetails loadUserByUsername(String userName);
    public User registerUsers(User user);
    User login (String email, String password);
}






package com.secondhand.secondhand.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.secondhand.secondhand.models.entities.Users;
public interface UserService {
    
    public Users addUser(Users newUser);
    void updateUser(Users user);
    void deleteUserById(Long id);
    Users getById(Long userId);
    List<Users> getAllUser();
    public UserDetails loadUserByUsername(String userName);
    public Users registerUsers(Users user);
    Users login (String email, String password);
}






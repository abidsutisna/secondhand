package com.secondhand.secondhand.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secondhand.secondhand.models.entities.Users;
import com.secondhand.secondhand.models.repos.UserRepository;
import com.secondhand.secondhand.utils.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users addUser(Users newUser) {
        return this.userRepository.save(newUser);
    }

    @Override
    public void updateUser(Users user) {
        this.userRepository.findById(user.getUserId()).get();
        this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public Users getById(Long userId) {
        return this.userRepository.findById(userId).get();
    }

    @Override
    public List<Users> getAllUser() {

        return this.userRepository.findAll() ;
    }
    
    @Override
    public UserDetails loadUserByUsername(String userName) {
        return userRepository.findByEmail(userName)
        .orElseThrow(() -> new UsernameNotFoundException("Users Not Found with email : " + userName));
    }

    @Override
    public Users registerUsers(Users user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new RuntimeException("Users already exists with email: " + user.getEmail());
        }

        String encodePassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);
        
        return userRepository.save(user);
    }

    @Override
    public Users login (String email, String password) {
        Users user = userRepository.findByEmailAndPassword(email, password);
        return user;
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}

package com.secondhand.secondhand.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secondhand.secondhand.models.entities.Produk;
import com.secondhand.secondhand.models.entities.User;
import com.secondhand.secondhand.models.repos.ProdukRepository;
import com.secondhand.secondhand.models.repos.UserRepository;
import com.secondhand.secondhand.utils.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User newUser) {
        return this.userRepository.save(newUser);
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.findById(user.getUserId()).get();
        this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User getById(Long userId) {
        return this.userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUser() {

        return this.userRepository.findAll() ;
    }
    
    @Override
    public UserDetails loadUserByUsername(String userName) {
        return userRepository.findByEmail(userName)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email : " + userName));
    }

    @Override
    public User registerUsers(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }

        String encodePassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);
        
        return userRepository.save(user);
    }
 
}

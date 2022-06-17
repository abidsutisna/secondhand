package com.secondhand.secondhand.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.dto.UserRegsiterDTO;
import com.secondhand.secondhand.models.entities.User;
import com.secondhand.secondhand.models.entities.UserRole;
import com.secondhand.secondhand.services.UserService;

import lombok.RequiredArgsConstructor;


@RestController 
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<User>> registerUser(@RequestBody @Valid UserRegsiterDTO userDTO, Errors errors){

        ResponseDTO<User> responseDTO = new ResponseDTO<>();

        //if error
        if(errors.hasErrors()){
        for (ObjectError error : errors.getAllErrors()) {
            //add message ke response data
            responseDTO.getMessage().add(error.getDefaultMessage());
        }
        responseDTO.setStatus(false);
        //null karena terjadi error
        responseDTO.setPayload(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getPassword());
        user.setPassword(userDTO.getPassword());
        user.setUserRole1(UserRole.BUYER);
        user.setUserRole2(UserRole.SELLER);

        responseDTO.setStatus(true);
        responseDTO.setPayload(userService.addUser(user) );
        responseDTO.getMessage().add("Succes register");
        return ResponseEntity.ok(responseDTO);
    }
}




package com.secondhand.secondhand.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.dto.UserDTO;
import com.secondhand.secondhand.dto.UserLoginDTO;
import com.secondhand.secondhand.dto.UserRegsiterDTO;
import com.secondhand.secondhand.models.entities.User;
import com.secondhand.secondhand.models.entities.UserRole;
import com.secondhand.secondhand.services.UserService;

@RestController 
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")

    public ResponseEntity<ResponseDTO<User>> registerUser(@RequestBody @Valid UserRegsiterDTO userRegisterDTO, Errors errors){


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

        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setUserRole1(UserRole.BUYER);
        user.setUserRole2(UserRole.SELLER);

        responseDTO.setStatus(true);

        responseDTO.setPayload(userService.registerUsers(user)) ;
        responseDTO.getMessage().add("Succes register");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid UserLoginDTO userLoginDTO,Errors error ) {
    
      ResponseDTO<User> responseDTO = new ResponseDTO<>();

     User oauthUser = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    
     if(Objects.nonNull(oauthUser)){
  
     return "redirect:/";
    
     } else {

     return "redirect:/login";
    
     }
    }

    @PostMapping("/logout")
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {
    
  
        return "redirect:/login";
    }

    //mengupdate user
    @PostMapping("/update")
    public ResponseEntity<ResponseDTO<User>> updateUser(@RequestBody @Valid User user , Errors errors) {  

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
    // User user = new User();

    // user.setUserId(userDTO.getUserId());
    // user.setName(userDTO.getNama());
    // user.setCity(userDTO.getKota());
    // user.setAddress(userDTO.getAlamat());
    // user.setPhoneNumber(userDTO.getNoHp());

    user.setName(userService.getById(user.getUserId()).getUsername());
    user.setUserRole1(UserRole.BUYER);
    user.setUserRole2(UserRole.SELLER);
    user.setEmail(userService.getById(user.getUserId()).getEmail());
    user.setPassword(userService.getById(user.getUserId()).getPassword());

    responseDTO.setStatus(true);
    userService.updateUser(user);
    responseDTO.setPayload(user);
    responseDTO.getMessage().add("Succes update user");
    return ResponseEntity.ok(responseDTO);
    }
    //mendapatkan semua data user
  @GetMapping
  public List<User> getAllUser(){
    return userService.getAllUser();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id){
      return userService.getById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUserById(@PathVariable Long id){
    try {
      userService.deleteUserById(id);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }

}




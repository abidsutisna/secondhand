package com.secondhand.secondhand.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.dto.ResponseDTO;
import com.secondhand.secondhand.dto.UserLoginDTO;
import com.secondhand.secondhand.dto.UserRegsiterDTO;
import com.secondhand.secondhand.dto.UserUpdateDTO;
import com.secondhand.secondhand.models.entities.History;
import com.secondhand.secondhand.models.entities.NotifikasiBid;
import com.secondhand.secondhand.models.entities.User;
import com.secondhand.secondhand.models.entities.UserRole;
import com.secondhand.secondhand.services.CloudinaryStorageService;
import com.secondhand.secondhand.services.HistoryService;
import com.secondhand.secondhand.services.NotifikasiSevice;
import com.secondhand.secondhand.services.UserService;
import com.secondhand.secondhand.utils.JwtUtil;

@RestController 
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CloudinaryStorageService cloudinaryStorageService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/home")
    public String welcome() {
        return "Welcome to secondhand";
    }

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
        History history = new History();

        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setUserRole1(UserRole.BUYER);
        user.setUserRole2(UserRole.SELLER);

        responseDTO.setStatus(true);
        responseDTO.setPayload(userService.registerUsers(user)) ;

        history.setHistoryId(user.getUserId());
        history.setUserId(user.getUserId());
        historyService.addHistory(history);
        responseDTO.getMessage().add("Succes register");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
      try {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));
        } catch (Exception ex) {
          throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(userLoginDTO.getEmail());
    }

    @PostMapping("/logout")
    public String logoutDo(HttpServletRequest request,HttpServletResponse response){
        return "redirect:/login";
    }

    //mengupdate user
    @PostMapping( value =  "/update" ,
                  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                  produces = {MediaType.APPLICATION_JSON_VALUE}) 
    public ResponseEntity<ResponseDTO<User>> updateUser(@ModelAttribute UserUpdateDTO userUpdateDTO, Errors errors) {  

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

    user.setName(userService.getById(userUpdateDTO.getUserId()).getUsername());
    user.setUserRole1(UserRole.BUYER);
    user.setUserRole2(UserRole.SELLER);
    user.setEmail(userService.getById(userUpdateDTO.getUserId()).getEmail());
    user.setPassword(userService.getById(userUpdateDTO.getUserId()).getPassword());

    user.setUserId(userUpdateDTO.getUserId());
    user.setCity(userUpdateDTO.getCity());
    user.setAddress(userUpdateDTO.getAddress());
    user.setPhoneNumber(userUpdateDTO.getPhoneNumber());

    
    Map<?,?> uploadImage = (Map<?,?>)cloudinaryStorageService.upload(userUpdateDTO.getProfileImage()).getData();
    user.setProfileImage(uploadImage.get("url").toString());

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




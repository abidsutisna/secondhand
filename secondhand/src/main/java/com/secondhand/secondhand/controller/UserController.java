package com.secondhand.secondhand.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondhand.secondhand.models.entities.User;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    private final User user;

}




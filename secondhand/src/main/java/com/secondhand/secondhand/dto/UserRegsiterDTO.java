package com.secondhand.secondhand.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegsiterDTO {
    @NotEmpty(message = "Username is required")
    private String name;

    @NotEmpty(message = "email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

}

package com.secondhand.secondhand.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private Long userId;

    private MultipartFile profileImage;

    private String city;

    private String address;

    private String phoneNumber;

}

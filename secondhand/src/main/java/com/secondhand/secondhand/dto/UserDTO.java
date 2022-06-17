package com.secondhand.secondhand.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @NotEmpty(message = "Nama is required")
    private String nama;

    @NotEmpty(message = "Kota is required")
    private String kota;

    @NotEmpty(message = "Alamat is required")
    private String alamat;
    
    @NotEmpty(message = "Number is required")
    private String noHp;
}

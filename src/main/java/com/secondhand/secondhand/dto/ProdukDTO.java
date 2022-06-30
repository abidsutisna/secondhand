package com.secondhand.secondhand.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdukDTO {
    private String produkName;

    private Integer hargaProduk;

    private String deskripsi;

    private List<MultipartFile> image;

    private long historyId;

    private long userId;
    
}

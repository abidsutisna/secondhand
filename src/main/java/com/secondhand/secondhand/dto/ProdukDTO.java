package com.secondhand.secondhand.dto;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.secondhand.secondhand.utils.StatusProdukEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdukDTO {
    private Long produkId;

    private String produkName;

    private Long hargaProduk;
    
    private Long categoryId;
    
    private String deskripsi;
    
    private List<MultipartFile> image;
    
    private long historyId;
    
    private long userId;

    private String statusTerjual;

    private StatusProdukEnum statusProduk;
    
}

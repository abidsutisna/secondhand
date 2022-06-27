package com.secondhand.secondhand.dto;

import java.util.List;

import com.secondhand.secondhand.models.entities.Image;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdukDTO {
    private String produkName;

    private Integer hargaProduk;

    private String deskripsi;

    private List<Image> image;

    private long historyId;

    private long userId;
    
}

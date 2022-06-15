package com.secondhand.secondhand.models.entities;

import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produk {
    private Long produkId;

    private String produkname;

    private Integer hargaProduk;

    private List<Category> categories;

    private String deskripsi;
    
    private List<Image> image;

}

package com.secondhand.secondhand.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinTable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produk implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produkId;

    private String produkname;

    private Integer hargaProduk;

    @ManyToMany
    @JoinTable(
        name="product_category",
        joinColumns = @JoinColumn(name="productId"),
        inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<Category> categories;

    private String deskripsi;
    
    @OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "produkId", referencedColumnName = "produkId")
    private List<Image> image;

    private Long wishlistId;
    
    private long historyId;

    private long userId;

}

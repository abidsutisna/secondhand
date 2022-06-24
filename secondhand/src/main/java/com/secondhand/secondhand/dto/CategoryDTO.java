package com.secondhand.secondhand.dto;

import java.util.List;
import javax.persistence.GenerationType;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.secondhand.secondhand.models.entities.Produk;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Produk> produk ;

}

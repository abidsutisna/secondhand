package com.secondhand.secondhand.dto;

import java.util.List;
import com.secondhand.secondhand.models.entities.Produk;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WishlistDTO {
    private long userId;
    private List<Produk> produkWishlist;

}

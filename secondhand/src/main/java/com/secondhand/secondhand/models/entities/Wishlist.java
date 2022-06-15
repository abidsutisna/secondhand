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
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    private Long wishlistId;
    private long userId;
    private List<Produk> produkWishlist;
}

package com.secondhand.secondhand.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    private long userId;
    
    @OneToMany(targetEntity = Produk.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "wishlistId", referencedColumnName = "wishlistId")
    private List<Produk> produkWishlist;
}

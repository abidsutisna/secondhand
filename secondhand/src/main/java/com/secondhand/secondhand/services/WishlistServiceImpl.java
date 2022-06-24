package com.secondhand.secondhand.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.secondhand.secondhand.models.entities.Produk;
import com.secondhand.secondhand.models.entities.Wishlist;
import com.secondhand.secondhand.models.repos.WishlistRepository;

@Service
public class WishlistServiceImpl implements WishlistService{
    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public Wishlist addWishlist(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }

    @Override
    public void updateWishlist(Wishlist wishlist) {
        this.wishlistRepository.findById(wishlist.getWishlistId()).get();
        this.wishlistRepository.save(wishlist);
    }

    @Override
    public void deleteWishlistById(Long id) {
        this.wishlistRepository.deleteById(id);
    }

    @Override
    public Wishlist getById(Long id) {
        return this.wishlistRepository.findById(id).get();
    }

    @Override
    public List<Wishlist> getAllWishlist() {
        return this.wishlistRepository.findAll();
    }

    @Override
    public void addProduk(Produk produk, Long wishlistId) {
        Wishlist wishlist = getById(wishlistId);
        if(wishlist == null ){   throw new RuntimeException("wis with ID: " + wishlistId + " Not found");
         
        }
        wishlist.getProduk().add(produk);
        addWishlist(wishlist);
        
    }
}
    


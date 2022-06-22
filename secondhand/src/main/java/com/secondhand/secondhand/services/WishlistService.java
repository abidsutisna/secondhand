package com.secondhand.secondhand.services;

import com.secondhand.secondhand.models.entities.Wishlist;
import java.util.List;
public interface WishlistService {
    public Wishlist addWishlist(Wishlist wishlist);
    void updateWishlist(Wishlist wishlist);
    void deleteWishlistById(Long id);
    Wishlist getById(Long wishlistId);
    List<Wishlist> getAllWishlist();
}

package com.secondhand.secondhand.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.secondhand.secondhand.models.entities.Produk;


@Repository
public interface ProdukRepository extends JpaRepository<Produk, Long>{

    @Query("SELECT p FROM Produk p WHERE p.produkname LIKE :produkname")
    public List<Produk> findProdukByName(@PathParam("produkname") String produkname);

    @Query("SELECT p FROM Produk p WHERE p.categoryId = :categoryId")
    public List<Produk> getProdukByCategory(@PathVariable Long categoryId);

    @Query("SELECT p FROM Produk p WHERE p.userId = :userId AND p.statusProduk =1")
    public List<Produk> getHistoryProduk(@PathVariable Long userId);

    @Query("SELECT p FROM Produk p WHERE p.userId = :userId AND p.statusWishlist =1")
    public List<Produk> getwishList(@PathVariable Long userId);
}

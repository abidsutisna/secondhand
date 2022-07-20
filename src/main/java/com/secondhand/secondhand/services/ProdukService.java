package com.secondhand.secondhand.services;

import java.util.List;

import com.secondhand.secondhand.models.entities.Category;
import com.secondhand.secondhand.models.entities.Produk;

public interface ProdukService {

    public Produk addProduk(Produk produk);
    void updateProduk(Produk produk);
    void deleteProdukById(Long id);
    Produk getById(Long produkId);
    List<Produk> getAllProduk();
    public List<Produk> findByProdukName (String name);
    public List<Produk> getProdukByCategory(Long categoryId);
    public List<Produk> getHistoryProduk(Long userId);
    public List<Produk> getwishList(Long userId);
}

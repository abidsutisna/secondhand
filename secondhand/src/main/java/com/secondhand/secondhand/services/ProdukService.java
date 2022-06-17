package com.secondhand.secondhand.services;

import java.util.List;

import com.secondhand.secondhand.models.entities.Produk;

public interface ProdukService {

    public Produk addProduk(Produk produk);
    void updateProduk(Produk produk);
    void deleteProdukById(Long id);
    Produk getById(Long produkId);
    List<Produk> getAllProduk();
    
}

package com.secondhand.secondhand.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondhand.secondhand.models.entities.Category;
import com.secondhand.secondhand.models.entities.Produk;
import com.secondhand.secondhand.models.repos.ProdukRepository;

@Service
public class ProdukServiceImpl implements ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    @Override
    public Produk addProduk(Produk produk) {
        return this.produkRepository.save(produk);
    }

    @Override
    public void updateProduk(Produk produk) {
        this.produkRepository.findById(produk.getProdukId()).get();
        this.produkRepository.save(produk);
    }

    @Override
    public void deleteProdukById(Long id) {
        this.produkRepository.deleteById(id);
    }

    @Override
    public Produk getById(Long id) {
        return this.produkRepository.findById(id).get();
    }

    @Override
    public List<Produk> getAllProduk() {
        return this.produkRepository.findAll();
    }

    @Override
    public List<Produk> findByProdukName(String name) {
        return produkRepository.findProdukByName("%"+name+"%");
    }

    @Override
    public List<Produk> getProdukByCategory(Long categoryId) {
        return produkRepository.getProdukByCategory(categoryId);
    }

    @Override
    public List<Produk> getHistoryProduk(Long userId) {
        return produkRepository.getHistoryProduk(userId);
    }   
}

package com.secondhand.secondhand.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.secondhand.secondhand.models.entities.Produk;


@Repository
public interface ProdukRepository extends JpaRepository<Produk, Long>{

    @Query("SELECT p FROM Produk p WHERE p.produkname LIKE :produkname")
    public List<Produk> findProdukByName(@PathParam("produkname") String produkname);
}

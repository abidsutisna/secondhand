package com.secondhand.secondhand.models.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String city;

    private String address;

    private Integer phoneNumber;

    private UserRole userRole;

    private History history;

    private List<NotifikasiBid> notifikasi;

    private List<Produk> produk;

    private Wishlist wishlist;
}

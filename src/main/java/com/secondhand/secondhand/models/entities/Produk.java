package com.secondhand.secondhand.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;

import org.springframework.web.multipart.MultipartFile;

import com.secondhand.secondhand.utils.StatusProdukEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produk implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produkId;

    private String produkname;

    private Long hargaProduk;

    private Long categoryId;
    
    private String categoryName;

    private String statusTerjual;
    
    private String deskripsi;
    
    @OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "produkId", referencedColumnName = "produkId")
    private List<MultipartFile> image;

    @OneToMany(targetEntity = Penawaran.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "produkId", referencedColumnName = "produkId")
    private List<Penawaran> penawaran;
    
    private long historyId;

    private long userId;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private StatusProdukEnum statusProduk = StatusProdukEnum.PUBLISH;

}

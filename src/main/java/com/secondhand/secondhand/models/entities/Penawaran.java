package com.secondhand.secondhand.models.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.exolab.castor.types.DateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.secondhand.secondhand.utils.StatusTawaranEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Penawaran implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penawaranId;

    private Long notifikasiId;

    private Long userId;

    private Long produkId;

    private String messageNotifikasi;

    private String time;

    private String imageUrl;

    private String produkName;

    private Long harga;

    private Long hargaPenawaran; 

    @JsonBackReference
    private String statusTerima;
    
    @Column
    @Enumerated(EnumType.ORDINAL)
    private StatusTawaranEnum statusTawaran = StatusTawaranEnum.NEGOSIASI;
}
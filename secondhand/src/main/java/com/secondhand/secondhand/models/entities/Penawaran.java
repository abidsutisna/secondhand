package com.secondhand.secondhand.models.entities;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Penawaran {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penawaranId;

    @OneToOne(targetEntity = History.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "penawaranId", referencedColumnName = "penawaranId")
    private Long notifikasiId;

    private Long userId;

    private Long produkId;

    private Long hargaPenawaran;

}

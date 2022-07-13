package com.secondhand.secondhand.dto;

import com.secondhand.secondhand.utils.StatusTawaranEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PenawaranDTO {
    
    private Long notifikasiId;

    private Long userId;

    private Long produkId;

    private Long hargaPenawaran;

    private StatusTawaranEnum statusTawaran;
}

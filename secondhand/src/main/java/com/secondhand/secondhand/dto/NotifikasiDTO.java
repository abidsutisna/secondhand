package com.secondhand.secondhand.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifikasiDTO {
    private Long bid;

    private Long produkId;

    private Integer hargaPenawaran;
    
    private Long userId;
}

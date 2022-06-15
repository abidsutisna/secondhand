package com.secondhand.secondhand.models.entities;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotifikasiBid {
    private Long bidId;

    private Long produkId;

    private Integer hargaPenawaran;
}

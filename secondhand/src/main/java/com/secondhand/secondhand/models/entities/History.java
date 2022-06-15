package com.secondhand.secondhand.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private Long historyId;
    
    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @OneToMany(targetEntity = Produk.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "produkId", referencedColumnName = "id")
    private List<Produk> produk;

}

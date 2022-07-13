package com.secondhand.secondhand.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable{

    @Id
    private Long historyId; 
    
    private Long userId;

    @OneToMany(targetEntity = Produk.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "historyId", referencedColumnName = "historyId" , nullable = true)
    private List<Produk> produkId; 

}
package com.secondhand.secondhand.models.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String profileImage;

    private String name;

    private String email;

    private String password;

    private String city;

    private String address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole userRole1;

    
    @Enumerated(EnumType.STRING)
    private UserRole userRole2;

    @OneToOne(targetEntity = History.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private History history;

    @OneToMany(targetEntity = NotifikasiBid.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private List<NotifikasiBid> notifikasi;

    
    @OneToMany(targetEntity = Penawaran.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private List<Penawaran> penawaran;

    @OneToMany(targetEntity = Produk.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private List<Produk> produk;

    @OneToOne(targetEntity = Wishlist.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Wishlist wishlist;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority(userRole1.name());
        SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority(userRole2.name());


        return Collections.singletonList(authority1) ;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

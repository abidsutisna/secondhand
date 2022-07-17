package com.secondhand.secondhand.models.entities;

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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{

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
    @JsonBackReference
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority("BUYER, SELLER");
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

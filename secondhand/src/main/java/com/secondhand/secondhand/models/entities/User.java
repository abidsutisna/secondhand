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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.scheduling.annotation.Schedules;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String email;

    private String password;

    private String city;

    private String address;

    private Integer phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToMany(targetEntity = Schedules.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "history_id", referencedColumnName = "userId")
    private History history;

    @OneToMany(targetEntity = Schedules.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "bidId", referencedColumnName = "userId")
    private List<NotifikasiBid> notifikasi;

    @OneToMany(targetEntity = Schedules.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "produkId", referencedColumnName = "userId")
    private List<Produk> produk;

    @OneToOne(targetEntity = Schedules.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "wishlistId", referencedColumnName = "userId")
    private Wishlist wishlist;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority) ;
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

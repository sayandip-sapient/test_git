package com.bank_customer.bank_customer.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String aadharNo;

    // Implementing UserDetails methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // For simplicity, let's assume every user has a ROLE_USER authority.
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return aadharNo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You may implement account expiration logic here.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You may implement account locking logic here.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You may implement credentials expiration logic here.
    }

    @Override
    public boolean isEnabled() {
        return true; // You may implement user activation/deactivation logic here.
    }
}

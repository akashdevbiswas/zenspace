package com.cromxt.enterprisekart.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
  USER,
  ADMIN;

  public Collection<? extends GrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = this.name();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s",role)));
        // this.authorities.forEach(authority->{
        //     grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        // });
        return grantedAuthorities;
    }
}

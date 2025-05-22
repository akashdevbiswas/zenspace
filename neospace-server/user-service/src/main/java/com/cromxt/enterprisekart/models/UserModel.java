package com.cromxt.enterprisekart.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@AllArgsConstructor
@Data
@Table(name = "users")
public class UserModel implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  @Enumerated(EnumType.STRING)
  private UserRole role;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }
}

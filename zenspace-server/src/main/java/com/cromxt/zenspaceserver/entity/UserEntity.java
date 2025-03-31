package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String email;
    private String username;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    private UserRole userRole;

    @Getter(AccessLevel.NONE)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getPermissions().stream().map(permission-> new SimpleGrantedAuthority(permission.name())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }
}

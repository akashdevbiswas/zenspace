package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Column(unique = true)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roleName")
    private UserRole userRole;

    @Getter(AccessLevel.NONE)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = userRole.getPermissions().stream().map(permissions -> new SimpleGrantedAuthority(permissions.name())).collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRoleName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

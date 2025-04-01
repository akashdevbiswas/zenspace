package com.cromxt.zenspaceserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRole {

    @Id
    private String roleName;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Getter(AccessLevel.NONE)
    private Set<PlatformPermissions> permissions;


    @OneToMany(mappedBy = "userRole")
    private Set<UserEntity> users;

    public Set<? extends GrantedAuthority> getAllGrantedAuthorities(){
        Set<SimpleGrantedAuthority> authorities = permissions.stream().map(permissions -> new SimpleGrantedAuthority(permissions.name())).collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+roleName));
        return authorities;
    }
}

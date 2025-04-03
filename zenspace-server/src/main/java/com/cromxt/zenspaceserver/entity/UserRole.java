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
    private Set<PlatformPermissions> permissions;


    @OneToMany(mappedBy = "userRole")
    private Set<UserEntity> users;
}

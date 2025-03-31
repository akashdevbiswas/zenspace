package com.cromxt.zenspaceserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRole {

    @Id
    private String roleId;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<PlatformPermissions> permissions;
}

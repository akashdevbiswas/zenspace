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
@Table(name = "space_rules")
public class SpaceRules {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<SpacePermissions> spacePermissions;
}

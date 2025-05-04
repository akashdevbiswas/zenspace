package com.zenspace.spaceservice.entity;


import lombok.*;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SpaceRules {

    @Id
    private String id;

    @DBRef
    private Set<SpacePermissions> spacePermissions;
}

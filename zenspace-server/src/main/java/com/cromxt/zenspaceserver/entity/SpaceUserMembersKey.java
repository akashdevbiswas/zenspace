package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@Getter
@Setter
public class SpaceUserMembersKey implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "space_id", referencedColumnName = "id")
    private Space space;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    private SpaceRules rule;
}

package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "space_user_members")
public class SpaceUserMembers {

    @EmbeddedId
    private SpaceUserCompositeKey id;
    @CreationTimestamp
    private LocalDate joinedAt;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    private Rule rule;
}

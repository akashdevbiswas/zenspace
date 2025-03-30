package com.cromxt.zenspaceserver.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "space_id", referencedColumnName = "id")
    private Space space;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Permissions> permission;

    @OneToOne(mappedBy = "rule")
    private SpaceUserMembers spaceUserMembers;
}

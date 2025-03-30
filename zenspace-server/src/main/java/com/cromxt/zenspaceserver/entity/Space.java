package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "space")
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "space")
    private Set<Rule> rule;
}

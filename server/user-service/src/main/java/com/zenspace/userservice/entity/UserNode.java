package com.zenspace.userservice.entity;



import jakarta.persistence.PrePersist;
import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Node
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserNode {

    @Id
    private String id;

    @Property(name = "created_at")
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @Relationship(type = "FRIEND")
    private Set<UserNode> friends = new HashSet<>();
}

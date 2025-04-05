package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "space_user_members")
public class SpaceUserMembers {
    @EmbeddedId
    private SpaceUserMembersKey id;

    @CreationTimestamp
    private LocalDate joinedAt;
}

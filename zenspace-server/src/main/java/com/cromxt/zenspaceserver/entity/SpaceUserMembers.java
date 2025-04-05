package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "space_user_members")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpaceUserMembers {
    @EmbeddedId
    private SpaceUserMembersKey id;

    @CreationTimestamp
    private LocalDate joinedAt;
}

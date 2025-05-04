package com.zenspace.spaceservice.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpaceUserMembers {
    
    private String id;
    private String spaceId;
    private String userId;
    @CreatedDate
    private LocalDate joinedAt;
}

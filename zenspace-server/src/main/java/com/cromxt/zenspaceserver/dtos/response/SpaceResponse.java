package com.cromxt.zenspaceserver.dtos.response;

import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpaceResponse{
    private String id;
    private String name;
    private String description;
    private String spaceProfileImage;
    private String ruleId;
    private LocalDate createdAt;
}

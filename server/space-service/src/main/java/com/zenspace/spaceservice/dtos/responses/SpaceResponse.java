package com.zenspace.spaceservice.dtos.responses;

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

package com.cromxt.zenspaceserver.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileResponse {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String profileImage;
}

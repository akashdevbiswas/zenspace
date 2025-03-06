package com.cromxt.zenspaceserver.dtos.response;

public record UserResponse (
        String id,
        String username,
        String firstName,
        String lastName,
        String email,
        String avatar,
        String dateOfBirth,
        String gender
){
}

package com.cromxt.zenspaceserver.dtos.request;

public record NewUser(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String dateOfBirth,
        String gender,
        String avatar
){
}

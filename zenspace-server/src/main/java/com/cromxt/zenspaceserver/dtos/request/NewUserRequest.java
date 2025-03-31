package com.cromxt.zenspaceserver.dtos.request;

public record NewUserRequest(
        String username,
        String password,
        String email
){
}

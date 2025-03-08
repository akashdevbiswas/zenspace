package com.cromxt.zenspaceserver.dtos.request;

public record UserCredential(
        String usernameOrEmail,
        String password
) {
}

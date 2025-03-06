package com.cromxt.zenspaceserver.dtos.response;

public record AuthTokens (
        String accessToken,
        String refreshToken
){
}

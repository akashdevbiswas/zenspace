package com.cromxt.zenspaceserver.dtos.request;

import org.springframework.web.multipart.MultipartFile;

public record NewUser(
        String username,
        String password,
        String email,
        String firstName,
        String lastName,
        String dateOfBirth,
        String gender,
        MultipartFile profileImage,
        Integer avatar
){
}

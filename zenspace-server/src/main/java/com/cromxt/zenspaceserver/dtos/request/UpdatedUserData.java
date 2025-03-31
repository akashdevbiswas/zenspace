package com.cromxt.zenspaceserver.dtos.request;

import org.springframework.web.multipart.MultipartFile;

public record UpdatedUserData (
        String firstName,
        String lastName,
        String dateOfBirth,
        String gender,
        MultipartFile profileImage,
        Integer avatar
){
}

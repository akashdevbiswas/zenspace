package com.cromxt.zenspaceserver.dtos.request;

import com.cromxt.zenspaceserver.entity.Gender;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record UserDataRequest(
        String firstName,
        String lastName,
        String bio,
        LocalDate dateOfBirth,
        Gender gender,
        MultipartFile profileImage
){
}

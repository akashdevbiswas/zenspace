package com.zenspace.spaceservice.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

public record SpaceRequest(
        String name,
        String description,
        MultipartFile spaceProfileImage
) {
}

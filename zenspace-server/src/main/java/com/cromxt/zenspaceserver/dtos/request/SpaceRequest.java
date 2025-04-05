package com.cromxt.zenspaceserver.dtos.request;

import org.springframework.web.multipart.MultipartFile;

public record SpaceRequest(
        String name,
        String description,
        MultipartFile spaceProfileImage
) {
}

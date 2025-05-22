package com.cromxt.enterprisekart.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

public record UserProfileRequest(
  String firstName,
  String lastName,
  String description,
  MultipartFile multipartFile
) {
  
}

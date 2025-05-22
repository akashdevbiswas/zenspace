package com.cromxt.enterprisekart.dtos.requests;

import org.springframework.web.multipart.MultipartFile;

import com.cromxt.enterprisekart.models.Gender;

public record UserProfileRequest(
  String firstName,
  String lastName,
  String description,
  Gender gender,
  MultipartFile multipartFile
) {
  
}

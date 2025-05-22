package com.cromxt.enterprisekart.dtos.responses;

public record UserProfileResponse(
  String id,
  String username,
  String firstName,
  String lastName,
  String description,
  String profileImage
) {
}

package com.cromxt.enterprisekart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.enterprisekart.dtos.requests.UserProfileRequest;
import com.cromxt.enterprisekart.dtos.responses.UserProfileResponse;
import com.cromxt.enterprisekart.service.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/profiles")
public class UserProfileController {

  private final UserProfileService userProfileService;

  @GetMapping(value = "/is-profile-complete")
  public ResponseEntity<Boolean> isUserProfileComplete(Authentication authentication) {
    Boolean result = userProfileService.isUserProfileComplete((String) authentication.getPrincipal());
    return ResponseEntity.ok(result);
  }

  @GetMapping
  public ResponseEntity<UserProfileResponse> getProfile(Authentication authentication) {
    return ResponseEntity.ok(userProfileService.getProfile((String) authentication.getPrincipal()));
  }

  @PutMapping(value = "/update")
  public ResponseEntity<UserProfileResponse> updateProfile(@RequestBody UserProfileRequest userProfileRequest,
      Authentication authentication) {
    String userId = (String) authentication.getPrincipal();
    return ResponseEntity.ok(userProfileService.updateProfile(userId, userProfileRequest));
  }

}

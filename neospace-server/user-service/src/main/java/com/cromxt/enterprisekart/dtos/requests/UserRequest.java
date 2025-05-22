package com.cromxt.enterprisekart.dtos.requests;

import com.cromxt.enterprisekart.models.UserRole;

public record UserRequest(
    String username,
    String password,
    UserRole role,
    String email,
    String dateOfBirth
    ) {

}

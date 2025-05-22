package com.cromxt.enterprisekart.dtos.requests;

import com.cromxt.enterprisekart.models.SpaceType;

public record SpaceRequest(
  String name,
  String description,
  SpaceType type
) {
}

package com.zenspace.spaceservice.service;

import com.zenspace.spaceservice.dtos.requests.SpaceRequest;
import com.zenspace.spaceservice.dtos.responses.SpacePageableResponse;
import com.zenspace.spaceservice.dtos.responses.SpaceResponse;

public interface SpaceService {
    SpaceResponse createSpace(SpaceRequest newSpace, String userId);

    SpacePageableResponse findSpacesBySpaceName(String spaceName, Integer pageNumber, Integer pageSize);

    SpacePageableResponse getAllSpacesByUserId(String userId, Integer pageNumber, Integer pageSize);

    void deleteSpace(String spaceId);

    SpaceResponse updateSpace(String spaceId, SpaceRequest spaceRequest);
}

package com.cromxt.zenspaceserver.service;

import com.cromxt.zenspaceserver.dtos.request.SpaceRequest;
import com.cromxt.zenspaceserver.dtos.response.SpacePageableResponse;
import com.cromxt.zenspaceserver.dtos.response.SpaceResponse;

public interface SpaceService {
    SpaceResponse createSpace(SpaceRequest newSpace, String userId);

    SpacePageableResponse findSpacesBySpaceName(String spaceName, Integer pageNumber, Integer pageSize);

    SpacePageableResponse getAllSpacesByUserId(String userId, Integer pageNumber, Integer pageSize);

    void deleteSpace(String spaceId);

    SpaceResponse updateSpace(String spaceId, SpaceRequest spaceRequest);

}

package com.cromxt.zenspaceserver.service.impl;


import com.cromxt.zenspaceserver.entity.Rule;
import com.cromxt.zenspaceserver.entity.Space;
import com.cromxt.zenspaceserver.entity.UserEntity;
import com.cromxt.zenspaceserver.respository.SpaceRepository;
import com.cromxt.zenspaceserver.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;
    private static final Rule rule = new Rule();

    @Override
    public Space createSpace(Space space, String userId) {
        UserEntity user = UserEntity.builder()
                .id(userId).build();
        Space newSpace = spaceRepository.save(space);
        return null;
    }
}

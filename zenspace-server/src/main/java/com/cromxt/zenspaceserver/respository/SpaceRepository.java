package com.cromxt.zenspaceserver.respository;

import com.cromxt.zenspaceserver.entity.Space;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends CrudRepository<Space, String> {

}

package com.cromxt.zenspaceserver.respository;

import com.cromxt.zenspaceserver.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, String> {

}

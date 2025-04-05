package com.cromxt.zenspaceserver.respository;

import com.cromxt.zenspaceserver.entity.SpaceUserMembers;
import com.cromxt.zenspaceserver.entity.SpaceUserMembersKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceUserMembersRepository extends JpaRepository<SpaceUserMembers, SpaceUserMembersKey> {

}

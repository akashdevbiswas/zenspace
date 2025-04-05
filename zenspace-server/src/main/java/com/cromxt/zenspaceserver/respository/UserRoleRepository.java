package com.cromxt.zenspaceserver.respository;

import com.cromxt.zenspaceserver.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}

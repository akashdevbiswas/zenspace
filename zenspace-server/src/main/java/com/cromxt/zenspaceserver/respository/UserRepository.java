package com.cromxt.zenspaceserver.respository;


import com.cromxt.zenspaceserver.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {
}

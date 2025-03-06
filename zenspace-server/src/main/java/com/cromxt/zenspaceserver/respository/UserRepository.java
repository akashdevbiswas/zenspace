package com.cromxt.zenspaceserver.respository;


import com.cromxt.zenspaceserver.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<UserEntity,String> {
}

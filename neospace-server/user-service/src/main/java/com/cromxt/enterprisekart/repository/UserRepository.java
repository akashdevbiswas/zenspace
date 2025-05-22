package com.cromxt.enterprisekart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cromxt.enterprisekart.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> { 

  Optional<UserModel> findByUsername(String username);

  

}

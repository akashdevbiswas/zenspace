package com.cromxt.enterprisekart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cromxt.enterprisekart.dtos.db.UserProfileDTO;
import com.cromxt.enterprisekart.models.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,String> {
  
  @Query(value = "SELECT * FROM users u JOIN user_profiles up on u.id = up.user_id WHERE u.id = :userId",nativeQuery = true)
  Optional<UserProfileDTO> getUserProfileDTO(String userId);
}

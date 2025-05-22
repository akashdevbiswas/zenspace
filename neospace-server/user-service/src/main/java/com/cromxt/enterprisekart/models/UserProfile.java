package com.cromxt.enterprisekart.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserProfile {
  @Id
  private String id;
  private String description;
  private String firstName;
  private String lastName;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  private String profileImage;


  // Maps UserModel id to UserProfile's id.
  @MapsId
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id")
  private UserModel user;
}

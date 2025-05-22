package com.cromxt.enterprisekart.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Space {
  
  @Id
  private String id;
  private String spaceName;
  private String spaceDescription;
  private SpaceType spaceType;
  @ManyToOne(cascade = CascadeType.DETACH)
  private UserModel spaceOwner;
}

package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "profiles")
public class UserProfile {

    @Id
    private String userId;

    @OneToOne
    @MapsId
    private UserEntity user;

    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    private String bio;

    @OneToOne
    @JoinColumn(name = "media_object_id", referencedColumnName = "id")
    private MediaObjects mediaId;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;



}

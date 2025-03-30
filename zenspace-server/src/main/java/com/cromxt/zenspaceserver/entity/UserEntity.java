package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String username;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    private String avatarUrl;
    @Column(name = "media_id")
    private String mediaId;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "ownerId",cascade = CascadeType.DETACH)
    private Set<Space> space;

}

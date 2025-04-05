package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "spaces")
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDate createdOn;
    @Column(name = "updated_on")
    @UpdateTimestamp
    private LocalDate updatedOn;
    @OneToOne
    @JoinColumn(name = "media_id", referencedColumnName = "id")
    private MediaObjects mediaObjects;
}

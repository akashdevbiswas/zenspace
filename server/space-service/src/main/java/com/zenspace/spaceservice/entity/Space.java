package com.zenspace.spaceservice.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Space {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    @CreatedDate
    @Indexed(name = "created_on")
    private LocalDate createdOn;
    @LastModifiedDate
    @Indexed(name = "updated_on")
    private LocalDate updatedOn;
}
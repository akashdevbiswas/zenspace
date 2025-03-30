package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class SpaceUserCompositeKey implements Serializable {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "space_id", referencedColumnName = "id")
    private Space spaceId;
}

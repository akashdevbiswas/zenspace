package com.cromxt.zenspaceserver.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserToUserCompositeKey implements Serializable {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "from_user_id", referencedColumnName = "id")
    private UserEntity fromUserId;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "to_user_id", referencedColumnName = "id")
    private UserEntity toUserId;
}

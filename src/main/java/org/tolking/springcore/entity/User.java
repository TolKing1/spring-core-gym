package org.tolking.springcore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode
public abstract class User {
    @NonNull protected String firstName;
    @NonNull protected String lastName;
    protected String userName;
    protected String password;
    protected boolean isActive;

    public void initialize() {
        this.userName = this.firstName + "." + this.lastName;
    }

}

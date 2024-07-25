package org.tolking.springcore.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class TrainingType {
    private final String name;

    public TrainingType(String name) {
        this.name = name;
    }
}

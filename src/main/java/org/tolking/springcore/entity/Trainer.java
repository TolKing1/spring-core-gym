package org.tolking.springcore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Trainer extends User{
    private long userId;
    private TrainingType specialization;

}

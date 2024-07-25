package org.tolking.springcore.entity;

import lombok.*;

import java.time.Duration;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Training {
    @NonNull private Long traineeId;
    @NonNull private Long trainerId;
    @NonNull private TrainingType trainingType;
    @NonNull private LocalDate date;
    @NonNull private Duration duration;
}

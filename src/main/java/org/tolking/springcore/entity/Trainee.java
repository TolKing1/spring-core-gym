package org.tolking.springcore.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class Trainee extends User{
    private long userId;
    private LocalDate dateOfBirth;
    private String address;
}

package org.tolking.springcore.service;

import org.tolking.springcore.entity.Trainee;

import java.util.List;

public interface TraineeService {
    List<Trainee> findAll();

    void create(Trainee trainee);

    void update(Trainee trainee);

    void delete(Long id);
}

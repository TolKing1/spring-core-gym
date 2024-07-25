package org.tolking.springcore.service;

import org.tolking.springcore.entity.Training;

import java.util.List;

public interface TrainingService {
    List<Training> findAll();

    void create(Training training);
}

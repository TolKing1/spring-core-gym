package org.tolking.springcore.service;

import org.tolking.springcore.entity.Trainer;

import java.util.List;

public interface TrainerService {
    List<Trainer> findAll();

    void create(Trainer trainer);

    void update(Trainer trainer);
}

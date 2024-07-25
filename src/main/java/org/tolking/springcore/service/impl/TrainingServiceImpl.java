package org.tolking.springcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tolking.springcore.dao.CR;
import org.tolking.springcore.entity.Training;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainingServiceImpl implements org.tolking.springcore.service.TrainingService {
    private final CR<Training> trainingDAO;

    @Override
    public List<Training> findAll(){
        try {
            return trainingDAO.getAll();
        }
        catch (IllegalArgumentException e){
            log.error("Training parse error: {}",e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void create(Training training){
        try {
            trainingDAO.create(training);
            log.info("Training has been created");
        }catch (NullPointerException e){
            log.warn("Training has not been created: {}", e.getMessage());
        }

    }
}

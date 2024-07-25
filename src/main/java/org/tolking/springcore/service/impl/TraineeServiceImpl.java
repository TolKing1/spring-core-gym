package org.tolking.springcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tolking.springcore.dao.CRUD;
import org.tolking.springcore.entity.Trainee;
import org.tolking.springcore.exception.UserFoundException;
import org.tolking.springcore.exception.UserNotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TraineeServiceImpl implements org.tolking.springcore.service.TraineeService {
    private final CRUD<Trainee> traineeDAO;

    @Override
    public List<Trainee> findAll(){
        try {
            return traineeDAO.getAll();
        }
        catch (IllegalArgumentException e){
            log.error("Trainee parse error: {}",e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void create(Trainee trainee){
        try {
            traineeDAO.create(trainee);
            log.info("Trainee with id = {} has been created", trainee.getUserId());
        }catch (NullPointerException e){
            log.warn("Trainee has not been created: {}", e.getMessage());
        }catch (UserFoundException e){
            log.warn(e.getMessage());
        }

    }

    @Override
    public void update(Trainee trainee){
        try {
            traineeDAO.update(trainee);
            log.info("Trainee with id = {} has been updated", trainee.getUserId());
        }catch (NullPointerException e){
            log.warn("Trainee with id = {} has not been updated  : {}", trainee.getUserId(), e.getMessage());
        }catch (UserNotFoundException e){
            log.warn(e.getMessage());
        }

    }

    @Override
    public void delete(Long id){
        try {
            traineeDAO.delete(id);
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
        }
    }
}

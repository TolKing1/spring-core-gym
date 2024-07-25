package org.tolking.springcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tolking.springcore.dao.CRU;
import org.tolking.springcore.entity.Trainer;
import org.tolking.springcore.exception.UserFoundException;
import org.tolking.springcore.exception.UserNotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerServiceImpl implements org.tolking.springcore.service.TrainerService {
    private final CRU<Trainer> trainerDAO;

    @Override
    public List<Trainer> findAll(){
        try {
            return trainerDAO.getAll();
        }
        catch (IllegalArgumentException e){
            log.error("Trainer parse error: {}",e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void create(Trainer trainer){
        try {
            trainerDAO.create(trainer);
            log.info("Training with id = {} has been created", trainer.getUserId());
        }catch (NullPointerException e){
            log.warn("Trainer has not been created: {}", e.getMessage());
        } catch (UserFoundException e) {
            log.warn(e.getMessage());
        }

    }

    @Override
    public void update(Trainer trainer){
        try {
            trainerDAO.update(trainer);
            log.info("Training with id = {} has been updated", trainer.getUserId());
        }catch (NullPointerException e){
            log.warn("Trainer with id = {} has not been updated  : {}", trainer.getUserId(), e.getMessage());
        } catch (UserNotFoundException e) {
            log.warn(e.getMessage());
        }

    }
}

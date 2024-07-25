package org.tolking.springcore.dao.impl;


import org.springframework.stereotype.Component;
import org.tolking.springcore.dao.CRUD;
import org.tolking.springcore.entity.Trainee;
import org.tolking.springcore.exception.UserFoundException;
import org.tolking.springcore.exception.UserNotFoundException;
import org.tolking.springcore.storage.Storage;
import org.tolking.springcore.storage.UserStorage;
import org.tolking.springcore.util.EntityMapperUtils;
import org.tolking.springcore.util.RandomString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TraineeDAO implements CRUD<Trainee> {
    private final ConcurrentHashMap<Long,Map<String, String>> trainerMap;
    private final ConcurrentHashMap<Long,Map<String, String>> entityMap;

    public TraineeDAO(Storage trainerStorage, Storage traineeStorage) {
        this.trainerMap = trainerStorage.getEntityMap();
        this.entityMap = traineeStorage.getEntityMap();
    }

    @Override
    public void delete(long id) throws UserNotFoundException {
        if (entityMap.get(id) == null){
            throw new UserNotFoundException(id);
        }

        entityMap.remove(id);
    }

    @Override
    public void update(Trainee entity) throws UserNotFoundException {
        Map<String, String> newRecord = EntityMapperUtils.toTraineeMap(entity);

        long userId = entity.getUserId();
        if (entityMap.get(userId) == null){
            throw new UserNotFoundException(userId);
        }

        entityMap.put(userId, newRecord);
    }


    @Override
    public List<Trainee> getAll() {
        List<Trainee> traineeList = new ArrayList<>();
        UserStorage userStorage = new UserStorage(trainerMap);
        Collection<Map<String, String>> records = new ArrayList<>(entityMap.values());

        for (Map<String, String> record : records){
            int serialNumber = userStorage.getSerialNumber(record);

            traineeList.add(EntityMapperUtils.toTraineeEntity(record, serialNumber));
        }

        return traineeList;
    }

    @Override
    public void create(Trainee entity) {
        if (entityMap.get(entity.getUserId()) != null){
            throw new UserFoundException(entity.getUserId());
        }

        //Generate password
        entity.setPassword(RandomString.getAlphaNumericString(10));

        Map<String, String> newRecord = EntityMapperUtils.toTraineeMap(entity);

        entityMap.put(entity.getUserId(), newRecord);
    }
}

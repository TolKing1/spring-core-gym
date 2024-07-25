package org.tolking.springcore.dao.impl;

import org.springframework.stereotype.Component;
import org.tolking.springcore.dao.CRU;
import org.tolking.springcore.entity.Trainer;
import org.tolking.springcore.exception.UserFoundException;
import org.tolking.springcore.exception.UserNotFoundException;
import org.tolking.springcore.storage.Storage;
import org.tolking.springcore.storage.UserStorage;
import org.tolking.springcore.util.EntityMapperUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TrainerDAO implements CRU<Trainer> {
    private final ConcurrentHashMap<Long,Map<String, String>> entityMap;
    private final ConcurrentHashMap<Long,Map<String, String>> traineeMap;

    public TrainerDAO(Storage trainerStorage, Storage traineeStorage) {
        this.entityMap = trainerStorage.getEntityMap();
        this.traineeMap = traineeStorage.getEntityMap();
    }

    @Override
    public void update(Trainer entity) throws UserNotFoundException {
        Map<String, String> newRecord = EntityMapperUtils.toTrainerMap(entity);

        long userId = entity.getUserId();
        if (entityMap.get(userId) == null){
            throw new UserNotFoundException(userId);
        }

        entityMap.put(userId, newRecord);
    }

    @Override
    public List<Trainer> getAll() {
        List<Trainer> trainerList = new ArrayList<>();
        UserStorage userStorage = new UserStorage(traineeMap);
        Collection<Map<String, String>> records = new ArrayList<>(entityMap.values());


        for (Map<String, String> record : records){
            int serialNumber = userStorage.getSerialNumber(record);

            trainerList.add(EntityMapperUtils.toTrainerEntity(record, serialNumber));
        }

        return trainerList;
    }

    @Override
    public void create(Trainer entity) throws UserFoundException {
        long userId = entity.getUserId();
        if (entityMap.get(userId) != null){
            throw new UserFoundException(userId);
        }

        Map<String, String> newRecord = EntityMapperUtils.toTrainerMap(entity);

        entityMap.put(userId, newRecord);
    }
}

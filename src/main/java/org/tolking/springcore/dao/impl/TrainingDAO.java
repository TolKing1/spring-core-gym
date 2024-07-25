package org.tolking.springcore.dao.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.tolking.springcore.dao.CR;
import org.tolking.springcore.entity.Training;
import org.tolking.springcore.storage.Storage;
import org.tolking.springcore.util.EntityMapperUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TrainingDAO implements CR<Training> {

    private final ConcurrentHashMap<Long,Map<String, String>> entityMap;

    public TrainingDAO(Storage trainingStorage) {
        this.entityMap = trainingStorage.getEntityMap();
    }

    @Override
    @SneakyThrows
    public List<Training> getAll() {
        List<Training> trainingList = new ArrayList<>();
        Collection<Map<String, String>> records = entityMap.values();

        for (Map<String, String> record : records){
            trainingList.add(EntityMapperUtils.toTrainingEntity(record));
        }

        return trainingList;
    }

    @Override
    public void create(Training entity){
        Map<String, String> newRecord = EntityMapperUtils.toTrainingMap(entity);

        entityMap.put((long) newRecord.hashCode(), newRecord);
    }

}

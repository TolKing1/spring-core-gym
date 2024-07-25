package org.tolking.springcore.util;

import lombok.experimental.UtilityClass;
import org.tolking.springcore.entity.Trainee;
import org.tolking.springcore.entity.Trainer;
import org.tolking.springcore.entity.Training;
import org.tolking.springcore.entity.User;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.tolking.springcore.util.Constants.*;

@UtilityClass
public class EntityMapperUtils {
    public static Trainee toTraineeEntity(Map<String, String> record, int serialNumber) {
        Trainee trainee = Trainee.builder()
                .userId(RecordUtils.parseLong(record, USER_ID))
                .firstName(record.get(FIRST_NAME))
                .lastName(record.get(LAST_NAME))
                .password(record.get(PASSWORD))
                .isActive(RecordUtils.parseBoolean(record, IS_ACTIVE))
                .address(record.get(ADDRESS))
                .dateOfBirth(RecordUtils.parseDate(record,DATE_BIRTH))
                .build();
        trainee.setUserName(getUsername(trainee));

        if (serialNumber > 0){
            trainee.setUserName(trainee.getUserName() +"_"+ serialNumber);
        }

        return trainee;
    }

    public static Trainer toTrainerEntity(Map<String, String> record, int serialNumber) {
        Trainer trainer = Trainer.builder()
                .userId(RecordUtils.parseLong(record, USER_ID))
                .firstName(record.get(FIRST_NAME))
                .lastName(record.get(LAST_NAME))
                .password(record.get(PASSWORD))
                .isActive(RecordUtils.parseBoolean(record, IS_ACTIVE))
                .specialization(RecordUtils.parseTrainingType(record, SPECIALIZATION))
                .build();
        trainer.setUserName(getUsername(trainer));

        if (serialNumber > 0){
            trainer.setUserName(trainer.getUserName() +"_"+ serialNumber);
        }

        return trainer;
    }

    // Convert a map to Training entity
    public static Training toTrainingEntity(Map<String, String> record) {
        return Training.builder()
                .traineeId(RecordUtils.parseLong(record, TRAINEE_ID))
                .trainerId(RecordUtils.parseLong(record, TRAINER_ID))
                .trainingType(RecordUtils.parseTrainingType(record, TRAINING_TYPE))
                .date(RecordUtils.parseDate(record, DATE))
                .duration(RecordUtils.parseDuration(record, DURATION))
                .build();
    }

    // Convert Trainer entity to map
    public static Map<String, String> toTraineeMap(Trainee entity) {
        Map<String, String> newRecord = toUserMap(entity);

        newRecord.put(USER_ID, MapUtils.toString(entity.getUserId()));
        newRecord.put(ADDRESS, entity.getAddress());
        newRecord.put(DATE_BIRTH, MapUtils.toString(entity.getDateOfBirth()));

        return newRecord;
    }

    // Convert Trainer entity to map
    public static Map<String, String> toTrainerMap(Trainer entity) {
        Map<String, String> newRecord = toUserMap(entity);

        newRecord.put(USER_ID, MapUtils.toString(entity.getUserId()));
        newRecord.put(SPECIALIZATION, MapUtils.toString(entity.getSpecialization()));

        return newRecord;
    }

    // Convert Training entity to map
    public static Map<String, String> toTrainingMap(Training entity) {
        Map<String, String> newRecord = new LinkedHashMap<>();
        newRecord.put(TRAINEE_ID, MapUtils.toString(entity.getTraineeId()));
        newRecord.put(TRAINER_ID, MapUtils.toString(entity.getTrainerId()));
        newRecord.put(DURATION, MapUtils.toString(entity.getDuration()));
        newRecord.put(TRAINING_TYPE, MapUtils.toString(entity.getTrainingType()));
        newRecord.put(DATE, MapUtils.toString(entity.getDate()));
        return newRecord;
    }

    private static Map<String, String> toUserMap(User entity) {
        Map<String, String> newRecord = new LinkedHashMap<>();
        newRecord.put(FIRST_NAME, entity.getFirstName());
        newRecord.put(LAST_NAME, entity.getLastName());
        newRecord.put(IS_ACTIVE, MapUtils.toString(entity.isActive()));
        newRecord.put(PASSWORD, entity.getPassword());
        return newRecord;
    }
    public static String getUsername(Map<String, String> record) {
        return record.get(FIRST_NAME) + "." + record.get(LAST_NAME);
    }
    public static String getUsername(User user) {
        return user.getFirstName() + "." + user.getLastName();
    }

    public static boolean hasDuplicates(Collection<Map<String, String>> records, Map<String, String> current) {

        String username = getUsername(current);
        long count = records.stream()
                .map(EntityMapperUtils::getUsername)
                .map(String::toLowerCase)
                .filter(username::equalsIgnoreCase)
                .count();

        return count > 1;
    }
}

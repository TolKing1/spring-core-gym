package org.tolking.springcore.util;

import lombok.experimental.UtilityClass;
import org.tolking.springcore.entity.TrainingType;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;

@UtilityClass
public class RecordUtils {
    public static long parseLong(Map<String, String> record, String key) {
        try {
            return Long.parseLong(record.get(key));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for key: " + key, e);
        }
    }

    public static boolean parseBoolean(Map<String, String> record, String key) {
        return Boolean.parseBoolean(record.get(key));
    }

    public static LocalDate parseDate(Map<String, String> record, String key) {
        try {
            return LocalDate.parse(record.get(key));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format for key: " + key, e);
        }
    }
    public static LocalDate parseLocalDate(Map<String, String> record, String key) {
        try {
            return LocalDate.parse(record.get(key));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format for key: " + key, e);
        }
    }

    public static Duration parseDuration(Map<String, String> record, String key) {
        return Duration.ofSeconds(parseLong(record, key));
    }

    public static TrainingType parseTrainingType(Map<String, String> record, String key) {
        return new TrainingType(record.get(key));
    }
}

package org.tolking.springcore.util;

import lombok.experimental.UtilityClass;
import org.tolking.springcore.entity.TrainingType;

import java.time.Duration;
import java.time.LocalDate;

@UtilityClass
public class MapUtils {
    public static String toString(Long value) {
        return value == null ? null : String.valueOf(value);
    }

    public static String toString(Boolean value) {
        return value == null ? null : String.valueOf(value);
    }

    public static String toString(Duration duration) {
        return duration == null ? null : String.valueOf(duration.getSeconds());
    }

    public static String toString(LocalDate date) {
        return date == null ? null : date.toString();
    }

    public static String toString(TrainingType type) {
        return type == null ? null : type.getName();
    }
}

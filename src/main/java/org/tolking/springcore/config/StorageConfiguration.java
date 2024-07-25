package org.tolking.springcore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tolking.springcore.storage.Storage;

@Configuration
public class StorageConfiguration {
    @Value("${csv.training}")
    private String trainingPath;

    @Value("${csv.trainer}")
    private String trainerPath;

    @Value("${csv.trainee}")
    private String traineePath;

    @Bean
    public Storage trainingStorage(){
        return new Storage(trainingPath);
    }

    @Bean
    public Storage trainerStorage(){
        return new Storage(trainerPath);
    }

    @Bean
    public Storage traineeStorage(){
        return new Storage(traineePath);
    }
}

package org.tolking.springcore;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.tolking.springcore.entity.Training;
import org.tolking.springcore.entity.TrainingType;
import org.tolking.springcore.service.TrainingService;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrainingServiceTest {

    @Autowired
    @Mock
    private TrainingService trainingService;

    private static List<Training> preparedData;
    @BeforeAll
    public static void init(){
        preparedData = new ArrayList<>(Arrays.asList(
                Training.builder()
                        .traineeId(1L)
                        .trainerId(2L)
                        .trainingType(new TrainingType("Cardio"))
                        .date(LocalDate.of(2024,7,17))
                        .duration(Duration.ofSeconds(3600))
                        .build(),

                Training.builder()
                        .traineeId(2L)
                        .trainerId(3L)
                        .trainingType(new TrainingType("Strength Training"))
                        .date(LocalDate.of(2024,7,18))
                        .duration(Duration.ofSeconds(4500))
                        .build()
        ));
    }
    @Test
    @Order(0)
    public void getAll() {
        List<Training> result = trainingService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(1)
    public void create() {
        Training newTraining = Training.builder()
                .traineeId(1L)
                .trainerId(2L)
                .trainingType(new TrainingType("High athletics"))
                .date(LocalDate.of(2077,7,17))
                .duration(Duration.ofSeconds(23425))
                .build();

        preparedData.add(newTraining);

        trainingService.create(newTraining);

        List<Training> result = trainingService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }
}


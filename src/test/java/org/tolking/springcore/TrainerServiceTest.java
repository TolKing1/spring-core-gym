package org.tolking.springcore;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.tolking.springcore.entity.Trainer;
import org.tolking.springcore.entity.TrainingType;
import org.tolking.springcore.service.TrainerService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrainerServiceTest {
    @Autowired
    @Mock
    private TrainerService trainerService;

    private static LinkedList<Trainer> preparedData;

    @BeforeAll
    public static void init(){
        preparedData = new LinkedList<>(
                Arrays.asList(
                        Trainer.builder()
                                .userId(1L)
                                .firstName("John")
                                .lastName("Doe")
                                .userName("John.Doe")
                                .isActive(true)
                                .specialization(new TrainingType("Strength"))
                                .password("password123")
                                .build(),

                        Trainer.builder()
                                .userId(2L)
                                .firstName("John")
                                .lastName("Doe")
                                .userName("John.Doe_1")
                                .isActive(false)
                                .specialization(new TrainingType("Cardio"))
                                .password("passw0rd")
                                .build()
                )
        );
    }

    @Test
    @Order(0)
    public void getAllWithDuplicateUsername() {

        List<Trainer> result = trainerService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(1)
    public void create() {
        Trainer newTrainer = Trainer.builder()
                .userId(3)
                .firstName("Tolek")
                .lastName("Bek")
                .userName("Tolek.Bek")
                .isActive(true)
                .specialization(new TrainingType("Aero"))
                .password("123")
                .build();

        preparedData.add(newTrainer);

        trainerService.create(newTrainer);

        List<Trainer> result = trainerService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(2)
    public void createWithSameId() {
        Trainer newTrainer = Trainer.builder()
                .userId(2)
                .firstName("Tolek")
                .lastName("Bek")
                .userName("Tolek.Bek")
                .isActive(true)
                .specialization(new TrainingType("Aero"))
                .password("123")
                .build();

        trainerService.create(newTrainer);

        List<Trainer> result = trainerService.findAll();

        //Check if same data after catching UserFoundException
        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(3)
    public void update() {
        Trainer newTrainer = Trainer.builder()
                .userId(3L)
                .firstName("TolKing")
                .lastName("Bek")
                .userName("TolKing.Bek")
                .isActive(false)
                .specialization(new TrainingType("Geo"))
                .password("123")
                .build();

        preparedData.removeLast();
        preparedData.add(newTrainer);

        trainerService.update(newTrainer);

        List<Trainer> result = trainerService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(4)
    public void updateWithInvalidId() {
        Trainer newTrainer = Trainer.builder()
                .userId(3232L)
                .firstName("TolKing")
                .lastName("Bek")
                .userName("TolKing.Bek")
                .isActive(false)
                .specialization(new TrainingType("Geo"))
                .password("123")
                .build();

        trainerService.update(newTrainer);

        List<Trainer> result = trainerService.findAll();

        //Check if same data after catching UserNotFoundException
        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }
}

package org.tolking.springcore;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.tolking.springcore.entity.Trainee;
import org.tolking.springcore.service.TraineeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TraineeServiceTest {
    @Autowired
    @Mock
    private TraineeService traineeService;

    private static LinkedList<Trainee> preparedData;

    @BeforeAll
    public static void init(){
        preparedData = new LinkedList<>(
                Arrays.asList(
                        Trainee.builder()
                                .userId(1L)
                                .firstName("John")
                                .lastName("Cage")
                                .userName("John.Cage")
                                .isActive(true)
                                .password("password001")
                                .dateOfBirth(LocalDate.of(1990,3,25))
                                .address("12 Apple St")
                                .build(),

                        Trainee.builder()
                                .userId(2L)
                                .firstName("Bob")
                                .lastName("White")
                                .userName("Bob.White")
                                .isActive(false)
                                .password("passw0rd999")
                                .dateOfBirth(LocalDate.of(1985,6,10))
                                .address("34 Cherry St")
                                .build()
                )
        );
    }

    @Test
    @Order(0)
    public void getAll() {

        List<Trainee> result = traineeService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(1)
    public void create() {
        Trainee newTrainee = Trainee.builder()
                .userId(3L)
                .firstName("Tolek")
                .lastName("Jan")
                .userName("Tolek.Jan")
                .isActive(false)
                .dateOfBirth(LocalDate.of(2003,6,10))
                .address("34 Meeh St")
                .build();

        preparedData.add(newTrainee);

        traineeService.create(newTrainee);

        List<Trainee> result = traineeService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(1)
    public void createWithSameId() {
        Trainee newTrainee = Trainee.builder()
                .userId(2L)
                .firstName("Tolek")
                .lastName("Jan")
                .userName("Tolek.Jan")
                .isActive(false)
                .dateOfBirth(LocalDate.of(2003,6,10))
                .address("34 Meeh St")
                .build();

        traineeService.create(newTrainee);

        List<Trainee> result = traineeService.findAll();
        //Check if same data after catching UserFoundException
        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(3)
    public void update() {
        List<Trainee> newTrainerList = preparedData;
        Trainee newTrainee = Trainee.builder()
                .userId(3L)
                .firstName("Tolek")
                .lastName("Jan")
                .userName("Tolek.Jan")
                .isActive(true)
                .password("pass1232131231231232313")
                .dateOfBirth(LocalDate.of(2003,6,10))
                .address("34 Meeh St")
                .build();

        preparedData.removeLast();
        preparedData.add(newTrainee);

        traineeService.update(newTrainee);

        List<Trainee> result = traineeService.findAll();

        assertTrue(result.containsAll(newTrainerList) && newTrainerList.containsAll(result));
    }

    @Test
    @Order(4)
    public void updateWithInvalidId() {
        List<Trainee> newTrainerList = preparedData;
        Trainee newTrainee = Trainee.builder()
                .userId(5435346L)
                .firstName("Tolek")
                .lastName("Jan")
                .userName("Tolek.Jan")
                .isActive(true)
                .password("pass1232131231231232313")
                .dateOfBirth(LocalDate.of(2003,6,10))
                .address("34 Meeh St")
                .build();

        traineeService.update(newTrainee);

        List<Trainee> result = traineeService.findAll();

        //Check if same data after catching UserNotFoundException
        assertTrue(result.containsAll(newTrainerList) && newTrainerList.containsAll(result));
    }

    @Test
    @Order(5)
    public void delete() {
        traineeService.delete(3L);
        preparedData.removeLast();

        List<Trainee> result = traineeService.findAll();

        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }

    @Test
    @Order(6)
    public void deleteWithInvalidId() {
        traineeService.delete(3213L);

        List<Trainee> result = traineeService.findAll();

        //Check if same data after catching UserNotFoundException
        assertTrue(result.containsAll(preparedData) && preparedData.containsAll(result));
    }
}
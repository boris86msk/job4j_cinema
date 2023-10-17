package ru.job4j.cinema.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.model.Halls;
import ru.job4j.cinema.repository.Sql2oHallsRepository;
import ru.job4j.cinema.repository.Sql2oUserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CinemaHallsServiceTest {
    private CinemaHallsService cinemaHallsService;
    private Sql2oHallsRepository hallsRepository;

    @BeforeEach
    public void initServices() {
        hallsRepository = mock(Sql2oHallsRepository.class);
        cinemaHallsService = new CinemaHallsService(hallsRepository);
    }
    @Test
    void wenGetHalls() {
        var listHalls = List.of(new Halls(), new Halls());
        when(hallsRepository.getAll()).thenReturn(listHalls);
        var returnList = cinemaHallsService.getHalls();
        assertThat(returnList).isEqualTo(listHalls);
    }
}
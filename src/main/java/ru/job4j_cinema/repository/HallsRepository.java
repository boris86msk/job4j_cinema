package ru.job4j_cinema.repository;

import ru.job4j_cinema.model.Halls;

import java.util.List;

public interface HallsRepository {
    List<Halls> gelAll();
}

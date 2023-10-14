package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Halls;

import java.util.List;

public interface HallsRepository {
    List<Halls> getAll();
}

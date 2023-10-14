package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.List;

public interface FilmsService {
    Collection<List<Film>> getAllForView();

    List<Film> getAll();
}

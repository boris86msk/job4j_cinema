package ru.job4j_cinema.service;

import ru.job4j_cinema.model.Film;

import java.util.Collection;
import java.util.List;

public interface FilmsService {
    Collection<Film> getAllFilms();
}

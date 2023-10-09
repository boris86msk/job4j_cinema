package ru.job4j_cinema.service;

import ru.job4j_cinema.model.FilmSession;

import java.util.Collection;

public interface FilmSessionService {
    Collection<FilmSession> getFilmSession();

    FilmSession getById(int id);
}

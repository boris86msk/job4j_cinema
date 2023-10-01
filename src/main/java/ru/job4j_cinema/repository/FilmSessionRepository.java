package ru.job4j_cinema.repository;

import ru.job4j_cinema.dto.FilmSessionDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionRepository {
    Collection<FilmSessionDto> findAll();
    Optional<FilmSessionDto> getById(int id);
}

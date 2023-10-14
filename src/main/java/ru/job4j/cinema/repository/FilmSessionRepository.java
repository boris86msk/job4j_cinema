package ru.job4j.cinema.repository;

import ru.job4j.cinema.dto.FilmSessionDto;

import java.util.List;
import java.util.Optional;

public interface FilmSessionRepository {
    List<FilmSessionDto> findAll();

    Optional<FilmSessionDto> getById(int id);
}

package ru.job4j_cinema.repository;

import ru.job4j_cinema.dto.FilmDto;

import java.util.Collection;

public interface FilmRepository {
    Collection<FilmDto> findAll();
}

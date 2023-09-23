package ru.job4j_cinema.repository;

import ru.job4j_cinema.dto.GenreDto;

import java.util.List;

public interface GenreRepository {
    List<GenreDto> findAllGenre();
}

package ru.job4j.cinema.repository;

import ru.job4j.cinema.dto.GenreDto;

import java.util.List;

public interface GenreRepository {
    List<GenreDto> findAllGenre();
}

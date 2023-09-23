package ru.job4j_cinema.repository;

import ru.job4j_cinema.dto.FileDto;

import java.util.List;

public interface FileRepository {
    List<FileDto> findAll();
}

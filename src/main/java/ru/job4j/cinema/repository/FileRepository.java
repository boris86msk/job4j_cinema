package ru.job4j.cinema.repository;

import ru.job4j.cinema.dto.FileDto;

import java.util.List;

public interface FileRepository {
    List<FileDto> findAll();
}

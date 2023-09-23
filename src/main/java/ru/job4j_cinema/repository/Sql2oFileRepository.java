package ru.job4j_cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j_cinema.dto.FileDto;
import ru.job4j_cinema.dto.FilmDto;

import java.util.Collection;
import java.util.List;

@Repository
public class Sql2oFileRepository implements FileRepository {
    private final Sql2o sql2o;
    public Sql2oFileRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<FileDto> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files");
            return query.setColumnMappings(FileDto.COLUMN_MAPPING).executeAndFetch(FileDto.class);
        }
    }
}

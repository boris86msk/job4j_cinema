package ru.job4j_cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j_cinema.dto.FilmDto;

import java.util.Collection;

@Repository
public class Sql2oFilmRepository implements FilmRepository {
    private final Sql2o sql2o;
    public Sql2oFilmRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<FilmDto> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films");
            return query.setColumnMappings(FilmDto.COLUMN_MAPPING).executeAndFetch(FilmDto.class);
        }
    }
}
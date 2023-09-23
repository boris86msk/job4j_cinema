package ru.job4j_cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j_cinema.dto.GenreDto;

import java.util.List;

@Repository
public class Sql2oGenreRepository implements GenreRepository {
    private final Sql2o sql2o;
    public Sql2oGenreRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<GenreDto> findAllGenre() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM genres");
            return query.setColumnMappings(GenreDto.COLUMN_MAPPING).executeAndFetch(GenreDto.class);
        }
    }
}

package ru.job4j_cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j_cinema.dto.FilmSessionDto;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions");
            return query.setColumnMappings(FilmSessionDto.COLUMN_MAPPING).executeAndFetch(FilmSessionDto.class);
        }
    }

    @Override
    public Optional<FilmSessionDto> getById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
            var session = query.setColumnMappings(FilmSessionDto.COLUMN_MAPPING)
                    .executeAndFetchFirst(FilmSessionDto.class);
            return Optional.ofNullable(session);
        }
    }
}

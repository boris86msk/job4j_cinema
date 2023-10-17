package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.dto.FilmSessionDto;

import java.util.List;
import java.util.Optional;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    private final Sql2o sql2o;

    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<FilmSessionDto> findAll() {
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

    /**
     * метод используется только для тестирования.
     */
    public void deleteAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM film_sessions");
            query.executeUpdate();
        }
    }
}

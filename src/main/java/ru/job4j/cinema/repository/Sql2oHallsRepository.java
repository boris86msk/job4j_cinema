package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Halls;

import java.util.List;

@Repository
public class Sql2oHallsRepository implements HallsRepository {
    private final Sql2o sql2o;

    public Sql2oHallsRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Halls> getAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls");
            return query.setColumnMappings(Halls.COLUMN_MAPPING).executeAndFetch(Halls.class);
        }
    }

    /**
     * метод используется только для тестирования.
     */
    public void deleteAllHalls() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM halls");
            query.executeUpdate();
        }
    }
}

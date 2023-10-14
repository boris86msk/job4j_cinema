package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.dto.FilmSessionDto;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oFilmSessionRepositoryTest {
    private static Sql2oFilmSessionRepository sql2oFilmSessionRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmSessionRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oFilmSessionRepository = new Sql2oFilmSessionRepository(sql2o);
    }

    @Test
    void wenFindAllFilmSessions() {
        var resList = Arrays.asList(new FilmSessionDto(1, 1, 2, LocalDateTime.of(2023,
                        Month.OCTOBER, 10, 18, 0, 0), LocalDateTime.of(2023,
                        Month.OCTOBER, 10, 20, 34, 0), 300),
                new FilmSessionDto(2, 2, 2, LocalDateTime.of(2023, Month.OCTOBER, 10,
                        20, 31, 0), LocalDateTime.of(2023, Month.OCTOBER, 10, 18,
                        20, 0), 270));
        var allFilmSessions = sql2oFilmSessionRepository.findAll();
        assertThat(allFilmSessions.get(0)).isEqualTo(resList.get(0));
        assertThat(allFilmSessions.get(1)).isEqualTo(resList.get(1));
    }

    @Test
    void wenGetFilmSessionsById() {
        int id = 2;
        var resFilmSessions = new FilmSessionDto(2, 2, 2, LocalDateTime.of(2023, Month.OCTOBER,
                10, 20, 31, 0), LocalDateTime.of(2023, Month.OCTOBER, 10,
                18, 20, 0), 270);
        var filmSessions = sql2oFilmSessionRepository.getById(id);
        assertThat(filmSessions.get()).isEqualTo(resFilmSessions);
    }

    @Test
    void wenNotFoundFilmSessionsById() {
        int id = 5;
        var filmSessions = sql2oFilmSessionRepository.getById(id);
        assertThat(filmSessions).isEmpty();
    }
}
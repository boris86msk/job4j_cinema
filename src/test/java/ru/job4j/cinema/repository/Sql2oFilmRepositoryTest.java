package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.dto.FilmDto;

import java.util.Arrays;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oFilmRepositoryTest {
    private static Sql2oFilmRepository sql2oFilmRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
    }

    @Test
    public void wenFindAllFilms() {
        var resList = Arrays.asList(new FilmDto(1, "someFilm", "description",
                        2000, 16, 100, 1, 1),
                new FilmDto(2, "someFilm2", "description2", 2001, 18,
                        100, 1, 1));
        var allFilms = sql2oFilmRepository.findAll();
        assertThat(allFilms.get(0)).isEqualTo(resList.get(0));
        assertThat(allFilms.get(1)).isEqualTo(resList.get(1));
    }
}
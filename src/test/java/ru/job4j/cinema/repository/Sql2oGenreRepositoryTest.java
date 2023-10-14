package ru.job4j.cinema.repository;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.dto.GenreDto;

import java.util.Arrays;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oGenreRepositoryTest {
    private static Sql2oGenreRepository sql2oGenreRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oGenreRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oGenreRepository = new Sql2oGenreRepository(sql2o);
    }


    @Test
    public void wenFindAllGenre() {
        var resList = Arrays.asList(new GenreDto(1, "someGenre"),
                new GenreDto(2, "someGenre2"));
        var allGenre = sql2oGenreRepository.findAllGenre();
        AssertionsForClassTypes.assertThat(allGenre.get(0)).isEqualTo(resList.get(0));
        AssertionsForClassTypes.assertThat(allGenre.get(1)).isEqualTo(resList.get(1));
    }
}
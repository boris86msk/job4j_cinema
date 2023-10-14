package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Halls;

import java.util.Arrays;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oHallsRepositoryTest {
    private static Sql2oHallsRepository sql2oHallsRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oHallsRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oHallsRepository = new Sql2oHallsRepository(sql2o);
    }

    @Test
    public void wenFindAllHalls() {
        var resList = Arrays.asList(new Halls(1, "красный", 18, 20,
                        "2D, мощное серео звучание, 2 основных входа/выхода"),
                new Halls(2, "синий", 14, 16, "3D, dolby digital, 1 основной"
                        + " вход/выход, кресла повышенной комфортности"));
        var allHalls = sql2oHallsRepository.getAll();
        assertThat(allHalls.get(0)).isEqualTo(resList.get(0));
        assertThat(allHalls.get(1)).isEqualTo(resList.get(1));
    }
}
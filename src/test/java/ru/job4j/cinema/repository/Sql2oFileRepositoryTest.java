package ru.job4j.cinema.repository;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.dto.FileDto;

import java.util.Arrays;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oFileRepositoryTest {
    private static Sql2oFileRepository sql2oFileRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFileRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oFileRepository = new Sql2oFileRepository(sql2o);
    }

    @Test
    public void wenFindAllFiles() {
        var resList = Arrays.asList(new FileDto(1, "someName", "somePath"),
                new FileDto(2, "someName2", "somePath2"));
        var allFiles = sql2oFileRepository.findAll();
        AssertionsForClassTypes.assertThat(allFiles.get(0)).isEqualTo(resList.get(0));
        AssertionsForClassTypes.assertThat(allFiles.get(1)).isEqualTo(resList.get(1));
    }

}
package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.User;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oUserRepositoryTest {
    private static Sql2oUserRepository sql2oUserRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void clearUser() {
        sql2oUserRepository.deleteAllUsers();
    }

    @Test
    public void whenSaveThenGetSame() {
        var user = sql2oUserRepository.save(new User("bob@mail.com", "Борис", "1111")).get();
        var savedUser = sql2oUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenFindUnsavedUser() {
        sql2oUserRepository.save(new User("name1@mail.com", "name1", "0000"));
        var noSavedUser = new User(0, "name2@mail.com", "name2", "1111");
        var searchUser = sql2oUserRepository.findByEmailAndPassword(noSavedUser.getEmail(), noSavedUser.getPassword());
        assertThat(searchUser).isEmpty();
    }

    @Test
    public void whenMatchEmail() {
        sql2oUserRepository.save(new User("boris2@mail.com", "Boris", "0000"));
        var savedUser2 = sql2oUserRepository.save(new User(0, "boris2@mail.com", "Jon", "1111"));
        assertThat(savedUser2).isEmpty();
    }
}
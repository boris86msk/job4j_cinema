package ru.job4j.cinema.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Tickets;

import java.util.Arrays;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class Sql2oTicketRepositoryTest {
    private static Sql2oTicketRepository sql2oTicketRepository;
    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oTicketRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }

    @Test
    public void wenGetListTicketsBySessions() {
        int sessionId = 1;
        var resList = Arrays.asList(new Tickets(1, 1, 2, 5, 1),
                new Tickets(2, 1, 2, 6, 1));
        var listTickets = sql2oTicketRepository.findBySession(sessionId);
        assertThat(listTickets.get(0)).isEqualTo(resList.get(0));
        assertThat(listTickets.get(1)).isEqualTo(resList.get(1));
    }

    @Test
    public void wenSuccessfulSaveNewTicket() {
        var newTickets = new Tickets(3, 1, 3, 5, 1);
        var savedTicket = sql2oTicketRepository.save(newTickets);
        assertThat(savedTicket).isNotEmpty();
        assertThat(savedTicket.get()).isEqualTo(newTickets);
        sql2oTicketRepository.deleteById(3);
    }
}
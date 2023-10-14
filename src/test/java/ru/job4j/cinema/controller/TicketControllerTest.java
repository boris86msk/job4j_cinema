package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.Tickets;
import ru.job4j.cinema.service.TicketsService;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.service.FilmSessionService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketControllerTest {
    private FilmSessionService filmSessionService;
    private TicketsService ticketsService;
    private TicketController ticketController;

    @BeforeEach
    public void initServices() {
        filmSessionService = mock(FilmSessionService.class);
        ticketsService = mock(TicketsService.class);
        ticketController = new TicketController(filmSessionService, ticketsService);
    }

    @Test
    void wenRequestChoosingPlacePage() {
        int enyId = 1;
        var filmSession = new FilmSession();
        var ticketsList = new ArrayList<Tickets>();
        List<List<String>> returnList = new ArrayList<>();
        when(filmSessionService.getById(enyId)).thenReturn(filmSession);
        when(ticketsService.getTicketsList(enyId)).thenReturn(ticketsList);
        when(ticketsService.getPlaceMap(ticketsList, filmSession)).thenReturn(returnList);

        var model = new ConcurrentModel();
        var view = ticketController.buyTicket(enyId, model);

        assertThat(view).isEqualTo("ticket");
    }
}
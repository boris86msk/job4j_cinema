package ru.job4j.cinema.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Tickets;

import java.util.List;
import java.util.Optional;

public interface TicketsService {
    List<Tickets> getTicketsList(int id);

    List<List<String>> getPlaceMap(List<Tickets> ticketsList, FilmSession session);

    Optional<Tickets> save(HttpServletRequest request);
}

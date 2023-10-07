package ru.job4j_cinema.repository;

import ru.job4j_cinema.model.Tickets;
import ru.job4j_cinema.model.FilmSession;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Optional<Tickets> save(Tickets dto);
    List<Tickets> findBySession(int id);

}

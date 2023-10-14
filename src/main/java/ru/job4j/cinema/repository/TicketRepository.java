package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Tickets;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Optional<Tickets> save(Tickets dto);

    List<Tickets> findBySession(int id);

}

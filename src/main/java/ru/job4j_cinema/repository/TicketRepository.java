package ru.job4j_cinema.repository;

import ru.job4j_cinema.dto.TicketsDto;

import java.util.Optional;

public interface TicketRepository {
    Optional<TicketsDto> save(TicketsDto dto);
}

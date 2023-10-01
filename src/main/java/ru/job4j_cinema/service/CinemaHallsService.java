package ru.job4j_cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j_cinema.model.Halls;
import ru.job4j_cinema.repository.HallsRepository;

import java.util.List;

@Service
public class CinemaHallsService implements HallsService {
    private final HallsRepository hallsRepository;

    public CinemaHallsService(HallsRepository hallsRepository) {
        this.hallsRepository = hallsRepository;
    }

    @Override
    public List<Halls> getHalls() {
        return hallsRepository.gelAll();
    }
}

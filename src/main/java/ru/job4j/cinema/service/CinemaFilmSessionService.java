package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Halls;
import ru.job4j.cinema.repository.FilmSessionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CinemaFilmSessionService implements FilmSessionService {
    private final FilmSessionRepository filmSessionRepository;
    private final FilmsService filmsService;

    private final HallsService hallsService;

    public CinemaFilmSessionService(FilmSessionRepository filmSessionRepository, FilmsService filmsService, HallsService hallsService) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmsService = filmsService;
        this.hallsService = hallsService;
    }

    @Override
    public Collection<FilmSession> getFilmSession() {
        Collection<FilmSessionDto> allFilmSessionDto = filmSessionRepository.findAll();
        List<FilmSession> filmSessionList = new ArrayList<>();
        for (FilmSessionDto fsd : allFilmSessionDto) {
            filmSessionList.add(mapping(fsd));
        }
        return filmSessionList;
    }

    @Override
    public FilmSession getById(int id) {
        var session = filmSessionRepository.getById(id).get();
        return mapping(session);
    }

    private FilmSession mapping(FilmSessionDto dto) {
        List<Film> allFilms = filmsService.getAll();
        List<Halls> halls = hallsService.getHalls();
        FilmSession filmSession = new FilmSession();
        filmSession.setId(dto.getId());
        filmSession.setFilm(allFilms.get(dto.getFilm() - 1));
        filmSession.setHalls(halls.get(dto.getHalls() - 1));
        filmSession.setStartSession(dto.getStartSession());
        filmSession.setEndSession(dto.getEndSession());
        filmSession.setPrice(dto.getPrice());
        return filmSession;
    }
}

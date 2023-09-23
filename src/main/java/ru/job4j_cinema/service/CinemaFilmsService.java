package ru.job4j_cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j_cinema.dto.FileDto;
import ru.job4j_cinema.dto.FilmDto;
import ru.job4j_cinema.dto.GenreDto;
import ru.job4j_cinema.model.Film;
import ru.job4j_cinema.repository.FileRepository;
import ru.job4j_cinema.repository.FilmRepository;
import ru.job4j_cinema.repository.GenreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CinemaFilmsService implements FilmsService {
    private final GenreRepository genreRepository;
    private final FilmRepository filmRepository;
    private final FileRepository fileRepository;

    public CinemaFilmsService(GenreRepository genreRepository, FilmRepository filmRepository, FileRepository fileRepository) {
        this.genreRepository = genreRepository;
        this.filmRepository = filmRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public Collection<Film> getAllFilms() {
        Collection<FilmDto> filmDtos = filmRepository.findAll();
        List<GenreDto> genreDtos = genreRepository.findAllGenre();
        List<FileDto> fileDtos = fileRepository.findAll();
        Collection<Film> filmList = new ArrayList<>();
        for (FilmDto filmDto : filmDtos) {
            Film film = new Film();
            film.setName(filmDto.getName());
            film.setDescription(filmDto.getDescription());
            film.setYear(filmDto.getYear());
            film.setMinimalAge(filmDto.getMinimalAge());
            film.setDurationInMinutes(filmDto.getDurationInMinutes());
            film.setGenre(genreDtos.get(filmDto.getGenre() - 1).getGenre());
            film.setFile(fileDtos.get(filmDto.getFile() - 1).getPath());
            filmList.add(film);
        }
        return filmList;
    }

}

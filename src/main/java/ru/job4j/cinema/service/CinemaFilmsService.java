package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.repository.FileRepository;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.dto.GenreDto;
import ru.job4j.cinema.model.Film;

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
    public Collection<List<Film>> getAllForView() {
        Collection<FilmDto> filmDtos = filmRepository.findAll();
        List<GenreDto> genreDtos = genreRepository.findAllGenre();
        List<FileDto> fileDtos = fileRepository.findAll();
        Collection<List<Film>> filmList = new ArrayList<>();
        List<Film> innerList = new ArrayList<>();
        int count = 0;
        for (FilmDto filmDto : filmDtos) {
            count++;
            Film film = new Film();
            film.setName(filmDto.getName());
            film.setDescription(filmDto.getDescription());
            film.setYear(filmDto.getYear());
            film.setMinimalAge(filmDto.getMinimalAge());
            film.setDurationInMinutes(filmDto.getDurationInMinutes());
            film.setGenre(genreDtos.get(filmDto.getGenre() - 1).getGenre());
            film.setFile(fileDtos.get(filmDto.getFile() - 1).getPath());
            if (innerList.size() < 2) {
                innerList.add(film);
                if (count == filmDtos.size()) {
                    filmList.add(innerList);
                    break;
                }
                continue;
            }
            filmList.add(innerList);
            innerList = new ArrayList<>();
            innerList.add(film);
        }
        return filmList;
    }

    @Override
    public List<Film> getAll() {
        Collection<FilmDto> filmDtos = filmRepository.findAll();
        List<GenreDto> genreDtos = genreRepository.findAllGenre();
        List<FileDto> fileDtos = fileRepository.findAll();
        List<Film> filmList = new ArrayList<>();
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

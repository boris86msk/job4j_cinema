package ru.job4j.cinema.dto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class FilmSessionDto {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "film_id", "film",
            "halls_id", "halls",
            "start_time", "startSession",
            "end_time", "endSession",
            "price", "price"
    );

    private int id;
    private int film;
    private int halls;
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private int price;

    public FilmSessionDto() {
    }

    public FilmSessionDto(int id, int film, int halls, LocalDateTime startSession, LocalDateTime endSession, int price) {
        this.id = id;
        this.film = film;
        this.halls = halls;
        this.startSession = startSession;
        this.endSession = endSession;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilm() {
        return film;
    }

    public void setFilm(int film) {
        this.film = film;
    }

    public int getHalls() {
        return halls;
    }

    public void setHalls(int halls) {
        this.halls = halls;
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public void setStartSession(LocalDateTime startSession) {
        this.startSession = startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public void setEndSession(LocalDateTime endSession) {
        this.endSession = endSession;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmSessionDto that = (FilmSessionDto) o;
        return id == that.id && film == that.film;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film);
    }
}

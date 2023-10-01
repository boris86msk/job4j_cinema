package ru.job4j_cinema.model;

import java.time.LocalDateTime;

public class FilmSession {
    private int id;
    private Film film;
    private Halls halls;
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private int price;

    public FilmSession() {
    }

    public FilmSession(int id, Film film, Halls halls, LocalDateTime startSession, LocalDateTime endSession, int price) {
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Halls getHalls() {
        return halls;
    }

    public void setHalls(Halls halls) {
        this.halls = halls;
    }

    public LocalDateTime getStartSession(LocalDateTime startSession) {
        return this.startSession;
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

    public String getTime() {
        return this.startSession.toLocalTime().toString();
    }
}

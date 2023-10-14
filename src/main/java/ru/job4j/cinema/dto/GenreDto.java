package ru.job4j.cinema.dto;

import java.util.Map;
import java.util.Objects;

public class GenreDto {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "genre"
    );
    private int id;
    private String genre;


    public GenreDto() {
    }

    public GenreDto(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GenreDto genreDto = (GenreDto) o;
        return id == genreDto.id && Objects.equals(genre, genreDto.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }
}

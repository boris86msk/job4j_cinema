package ru.job4j.cinema.dto;

import java.util.Map;
import java.util.Objects;

public class FileDto {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "path", "path"
    );
    private int id;
    public String name;
    private String path;

    public FileDto() {
    }

    public FileDto(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileDto fileDto = (FileDto) o;
        return id == fileDto.id && Objects.equals(name, fileDto.name) && Objects.equals(path, fileDto.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path);
    }
}

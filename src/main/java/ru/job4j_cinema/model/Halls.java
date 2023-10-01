package ru.job4j_cinema.model;

import java.util.Map;

public class Halls {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "halls_name", "name",
            "row_count", "row",
            "place_count", "placeCount",
            "description", "description"
    );
    private int id;
    private String name;
    private int row;
    private int placeCount;
    private String description;

    public Halls() {
    }

    public Halls(int id, String name, int row, int placeCount, String description) {
        this.id = id;
        this.name = name;
        this.row = row;
        this.placeCount = placeCount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

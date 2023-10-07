package ru.job4j_cinema.model;

import java.util.Map;

public class Tickets {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "session_id", "sessionId",
            "row_number", "row",
            "place_number", "place",
            "user_id", "userId"
    );
    private int id;
    private int sessionId;
    private int row;
    private int place;
    private int userId;

    public Tickets() {
    }

    public Tickets(int id, int sessionId, int row, int place, int userId) {
        this.id = id;
        this.sessionId = sessionId;
        this.row = row;
        this.place = place;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

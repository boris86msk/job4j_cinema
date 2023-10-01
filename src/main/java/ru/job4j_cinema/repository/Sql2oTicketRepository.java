package ru.job4j_cinema.repository;

import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j_cinema.dto.TicketsDto;

import java.util.Optional;

public class Sql2oTicketRepository implements TicketRepository {
    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<TicketsDto> save(TicketsDto dto) {
        try (var connection = sql2o.open()) {
            var sql = """
                      INSERT INTO tickets(session_id, row_number, place_number, user_id)
                      VALUES (:sessionId, :rowNumber, :placeNumber, :userId)
                      """;
            var query = connection.createQuery(sql, true)
                    .addParameter("sessionId", dto.getSessionId())
                    .addParameter("rowNumber", dto.getRow())
                    .addParameter("placeNumber", dto.getPlace())
                    .addParameter("userId", dto.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            dto.setId(generatedId);
            return Optional.of(dto);
        } catch (Sql2oException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

package ru.job4j_cinema.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import ru.job4j_cinema.model.FilmSession;
import ru.job4j_cinema.model.Tickets;
import ru.job4j_cinema.model.User;
import ru.job4j_cinema.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaTicketsService implements TicketsService {

    private final TicketRepository ticketRepository;

    public CinemaTicketsService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Tickets> getTicketsList(int id) {
        List<Tickets> bySession = ticketRepository.findBySession(id);
        return bySession;
    }

    @Override
    public List<List<String>> getPlaceMap(List<Tickets> ticketsList, FilmSession session) {
        int row = session.getHalls().getRow();
        int coll = session.getHalls().getPlaceCount();

        int[][] array = new int[row][coll];
        for (Tickets ticket : ticketsList) {
            array[ticket.getRow() - 1][ticket.getPlace() - 1] = ticket.getPlace();
        }

        List<List<String>> returnList = new ArrayList<>();
        List<String> innerList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coll; j++) {
                String place = String.valueOf(j + 1);
                if (j < 9) {
                    place = "0" + place;
                }
                if (array[i][j] != 0) {
                    place = "*" + place;
                }
                innerList.add(place);
            }
            returnList.add(i, innerList);
            innerList = new ArrayList<>();
        }
        return returnList;
    }

    @Override
    public Optional<Tickets> save(HttpServletRequest request) {
        Tickets tickets = new Tickets();
        tickets.setSessionId(Integer.parseInt(request.getParameter("session_id")));
        tickets.setRow(Integer.parseInt(request.getParameter("row")));
        tickets.setPlace(Integer.parseInt(request.getParameter("coll")));
        User user = (User) request.getSession().getAttribute("user");
        tickets.setUserId(user.getId());
        return ticketRepository.save(tickets);
    }
}

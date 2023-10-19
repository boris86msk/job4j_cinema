package ru.job4j.cinema.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sql2o.Sql2oException;
import ru.job4j.cinema.model.Tickets;
import ru.job4j.cinema.service.TicketsService;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.service.FilmSessionService;

import java.util.List;

@Controller
@RequestMapping("/ticket")
@ThreadSafe
public class TicketController {
    private final FilmSessionService filmSessionService;
    private final TicketsService ticketsService;

    public TicketController(FilmSessionService filmSessionService, TicketsService ticketsService) {
        this.filmSessionService = filmSessionService;
        this.ticketsService = ticketsService;
    }

    @GetMapping("/{sessionId}")
    public String buyTicket(@PathVariable int sessionId, Model model) {
        FilmSession filmSession = filmSessionService.getById(sessionId);
        List<Tickets> ticketsList = ticketsService.getTicketsList(filmSession.getId());
        List<List<String>> placeMap = ticketsService.getPlaceMap(ticketsList, filmSession);

        model.addAttribute("tickets", ticketsList);
        model.addAttribute("map", placeMap);
        model.addAttribute("session_id", sessionId);
        return "ticket";
    }

    @PostMapping("/buy")
    public String buyTicket(HttpServletRequest request, Model model) {
        try {
            var saveTicket = ticketsService.save(request);
            var filmSession = request.getParameter("session_id");
            var byId = filmSessionService.getById(Integer.parseInt(filmSession));
            model.addAttribute("ticket", saveTicket.get());
            model.addAttribute("filmSession", byId);
            return "good_buy";
        } catch (Sql2oException e) {
            model.addAttribute("message", e);
            return "errors/404";
        }
    }
}

package ru.job4j_cinema.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j_cinema.model.FilmSession;
import ru.job4j_cinema.model.Tickets;
import ru.job4j_cinema.model.User;
import ru.job4j_cinema.service.FilmSessionService;
import ru.job4j_cinema.service.TicketsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        model.addAttribute("tickets" ,ticketsList);
        model.addAttribute("map", placeMap);
        model.addAttribute("session_id", sessionId);
        return "ticket";
    }

    @PostMapping("/buy")
    public String buyTicket(HttpServletRequest request, Model model) {
        var saveTicket = ticketsService.save(request);
        var filmSession = request.getParameter("session_id");
        var byId = filmSessionService.getById(Integer.parseInt(filmSession));
        if (saveTicket.isEmpty()) {
            model.addAttribute("message", "Неудачная попытка покупки билета");
            return "errors/404";
        }
        model.addAttribute("ticket", saveTicket.get());
        model.addAttribute("filmSession", byId);
        return "good_buy";
    }
}

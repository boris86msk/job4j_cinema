package ru.job4j_cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j_cinema.model.FilmSession;
import ru.job4j_cinema.model.Halls;
import ru.job4j_cinema.service.FilmSessionService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ticket")
@ThreadSafe
public class TicketController {
    private final FilmSessionService filmSessionService;

    public TicketController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/{sessionId}")
    public String buyTicket(@PathVariable int sessionId, Model model) {
        FilmSession byId = filmSessionService.getById(sessionId);

        List<String> listRow = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14");
        List<String> listCol = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16");

        model.addAttribute("row", listRow);
        model.addAttribute("coll", listCol);
        return "ticket";
    }
}

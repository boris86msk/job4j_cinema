package ru.job4j.cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.FilmSessionService;

@Controller
@RequestMapping("/film_session")
@ThreadSafe
public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping()
    public String getFilmSession(Model model) {
        var filmSession = filmSessionService.getFilmSession();
        model.addAttribute("filmSession", filmSession);
        return "film session";
    }
}

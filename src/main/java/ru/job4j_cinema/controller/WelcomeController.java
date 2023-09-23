package ru.job4j_cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j_cinema.service.FilmsService;

@Controller
@ThreadSafe
public class WelcomeController {
    private final FilmsService filmsService;

    public WelcomeController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @GetMapping({"/", "/welcome"})
    public String getIndex() {
        return "welcome";
    }

    //тестовый метод
    @GetMapping("index")
    public String getMain(Model model) {
        model.addAttribute("films", filmsService.getAllFilms());
        return "index";
    }
}

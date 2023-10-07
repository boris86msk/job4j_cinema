package ru.job4j_cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j_cinema.service.FilmsService;

@Controller
@ThreadSafe
public class IndexController {
    private final FilmsService filmsService;

    public IndexController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @GetMapping({"/", "/welcome"})
    public String getIndex() {
        return "welcome";
    }

    @GetMapping("index")
    public String getMain(Model model) {
        model.addAttribute("films", filmsService.getAllForView());
        return "index";
    }
}

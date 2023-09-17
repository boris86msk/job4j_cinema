package ru.job4j_cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ThreadSafe
public class IndexController {

    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }

    //тестовый метод
    @GetMapping("main")
    public String getMain() {
        return "main";
    }
}

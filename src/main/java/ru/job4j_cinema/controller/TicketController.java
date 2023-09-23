package ru.job4j_cinema.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
@ThreadSafe
public class TicketController {
    @GetMapping()
    public String buyTicket() {
        return "welcome";
    }
}

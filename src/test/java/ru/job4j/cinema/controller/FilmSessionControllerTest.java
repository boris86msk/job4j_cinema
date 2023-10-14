package ru.job4j.cinema.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.service.FilmSessionService;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmSessionControllerTest {

    @Test
    void wenRequestFilmSessionPage() {
        var filmSessionService = mock(FilmSessionService.class);
        var filmSessionController = new FilmSessionController(filmSessionService);
        var list = new ArrayList<FilmSession>();
        when(filmSessionService.getFilmSession()).thenReturn(list);

        var model = new ConcurrentModel();
        var view = filmSessionController.getFilmSession(model);

        assertThat(view).isEqualTo("film session");
    }
}
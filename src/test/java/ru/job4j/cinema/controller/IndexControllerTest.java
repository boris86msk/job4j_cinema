package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.service.FilmsService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class IndexControllerTest {
    private FilmsService filmsService;
    private IndexController indexController;

    @BeforeEach
    public void initServices() {
        filmsService = mock(FilmsService.class);
        indexController = new IndexController(filmsService);
    }

    @Test
    void wenIndexRequest() {
        var model = new ConcurrentModel();
        var view = indexController.getIndexPage(model);
        assertThat(view).isEqualTo("index");
    }

    @Test
    void wenWelcomeRequest() {
        var view = indexController.getWelcomePage();
        assertThat(view).isEqualTo("welcome");
    }
}
package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void initServices() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void wenRequestRegistrationPage() {
        var view = userController.getRegistrationPage();
        assertThat(view).isEqualTo("users/register");
    }

    @Test
    public void whenSuccessfullyNewUserRegistered() {
        var user = new User();
        var captor = ArgumentCaptor.forClass(User.class);
        when(userService.save(captor.capture())).thenReturn(Optional.of(user));
        when(userService.findByEmailAndPassword(any(), any())).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var request = new MockHttpServletRequest();
        var view = userController.register(model, user, request);
        var actualUser = captor.getValue();

        assertThat(view).isEqualTo("redirect:/index");
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    public void whenUserHasNotRegistered() {
        var user = new User();
        when(userService.save(any())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var request = new MockHttpServletRequest();
        var view = userController.register(model, user, request);
        var errorMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("errors/404");
        assertThat(errorMessage).isEqualTo("Пользователь с такой почтой уже существует");
    }

    @Test
    public void wenRequestLoginPage() {
        var view = userController.getLoginPage();
        assertThat(view).isEqualTo("users/login");
    }

    @Test
    public void whenSuccessfullyUserLogin() {
        var user = new User(1, "moc@mail.ru", "Boris", "0000");
        var emailCaptor = ArgumentCaptor.forClass(String.class);
        var passCaptor = ArgumentCaptor.forClass(String.class);
        when(userService.findByEmailAndPassword(emailCaptor.capture(), passCaptor.capture())).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var request = new MockHttpServletRequest();
        var view = userController.loginUser(user, model, request);

        assertThat(view).isEqualTo("redirect:/index");
        assertThat(emailCaptor.getValue()).isEqualTo(user.getEmail());
        assertThat(passCaptor.getValue()).isEqualTo(user.getPassword());
    }

    @Test
    public void whenUnsuccessfulLoginUser() {
        var user = new User();
        when(userService.findByEmailAndPassword(any(), any())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var request = new MockHttpServletRequest();
        var view = userController.loginUser(user, model, request);
        var actualMessage = model.getAttribute("error");

        assertThat(view).isEqualTo("users/login");
        assertThat(actualMessage).isEqualTo("Почта или пароль введены неверно");
    }

    @Test
    public void wenRequestLogout() {
        var session = new MockHttpSession();
        var view = userController.logout(session);
        assertThat(view).isEqualTo("redirect:/users/login");
    }
}
package ru.job4j.cinema.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.Sql2oUserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CinemaUserServiceTest {
    private CinemaUserService userService;
    private Sql2oUserRepository userRepository;

    @BeforeEach
    public void initServices() {
        userRepository = mock(Sql2oUserRepository.class);
        userService = new CinemaUserService(userRepository);
    }

    @Test
    void wenSuccessfulSaveUser() {
        var user = new User();
        var captor = ArgumentCaptor.forClass(User.class);
        when(userRepository.save(captor.capture())).thenReturn(Optional.of(user));
        var actualUser = userService.save(user).get();
        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    void wenFindByEmailAndPassword() {
        var user = new User(1, "moc@mail.ru", "Boris", "0000");
        var emailCaptor = ArgumentCaptor.forClass(String.class);
        var passCaptor = ArgumentCaptor.forClass(String.class);
        when(userRepository.findByEmailAndPassword(emailCaptor.capture(), passCaptor.capture())).thenReturn(Optional.of(user));

        var actualUser = userService.findByEmailAndPassword(user.getEmail(), user.getPassword()).get();

        assertThat(actualUser).isEqualTo(user);
        assertThat(emailCaptor.getValue()).isEqualTo(user.getEmail());
        assertThat(passCaptor.getValue()).isEqualTo(user.getPassword());
    }
}
package ru.job4j_cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j_cinema.model.User;
import ru.job4j_cinema.repository.UserRepository;

import java.util.Optional;

@Service
public class CinemaUserService implements UserService {
    private final UserRepository userRepository;

    public CinemaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}

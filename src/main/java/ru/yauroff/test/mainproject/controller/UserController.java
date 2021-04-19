package ru.yauroff.test.mainproject.controller;

import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;

import java.util.UUID;

public class UserController {
    UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    public void create(String name) {
        User user = new User(UUID.randomUUID().getMostSignificantBits());
        repository.create(user);
    }

    public void update(User region, String name) {
        //region.setName(name);
        repository.update(region);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

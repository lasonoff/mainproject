package ru.yauroff.test.mainproject.controller;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.model.Role;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;
import ru.yauroff.test.mainproject.repository.impl.ObjectRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    UserRepository repository = ObjectRepository.getInstance().getUserRepository();

    public void create(String firstName, String lastName, Region region, List<Post> posts, Role role) {
        User user = new User(new Date().getTime());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setRegionId(region.getId());
        user.setPostsId(posts.stream().map(Post::getId).collect(Collectors.toList()));
        repository.create(user);
    }

    public void update(User user, String firstName, String lastName, Region region, List<Post> posts, Role role) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setRegionId(region.getId());
        user.setPostsId(posts.stream().map(Post::getId).collect(Collectors.toList()));
        repository.update(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

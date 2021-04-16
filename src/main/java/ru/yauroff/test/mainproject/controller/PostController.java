package ru.yauroff.test.mainproject.controller;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.repository.PostRepository;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ayaurov on 16.04.2021.
 */
public class PostController {
    PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    public void create(String content) {
        Date date = new Date();
        Post post = new Post(UUID.randomUUID().getMostSignificantBits(), content, date, date);
        repository.create(post);
    }

    public void update(Post post, String content) {
        post.setUpdated(new Date());
        post.setContent(content);
        repository.update(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

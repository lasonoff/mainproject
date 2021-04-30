package ru.yauroff.test.mainproject.controller;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.repository.PostRepository;
import ru.yauroff.test.mainproject.repository.impl.ObjectRepository;

import java.util.Date;


public class PostController {
    PostRepository repository = ObjectRepository.getInstance().getPostRepository();

    public void create(String content) {
        Date date = new Date();
        Post post = new Post(new Date().getTime(), content, date, date);
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

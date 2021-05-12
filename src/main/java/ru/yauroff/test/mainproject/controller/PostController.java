package ru.yauroff.test.mainproject.controller;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.PostRepository;
import ru.yauroff.test.mainproject.repository.impl.ObjectRepository;


public class PostController {
    PostRepository repository = ObjectRepository.getInstance().getPostRepository();

    public void create(String content, User user) {
        Post post = new Post(content);
        post.setUser(user);
        repository.create(post);
    }

    public void update(Post post, String content) {
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

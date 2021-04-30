package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.repository.PostRepository;
import ru.yauroff.test.mainproject.repository.RegionRepository;
import ru.yauroff.test.mainproject.repository.UserRepository;

public class ObjectRepository {
    private static ObjectRepository instance;
    private PostRepository postRepository;
    private RegionRepository regionRepository;
    private UserRepository userRepository;

    private ObjectRepository() {
        postRepository = new JsonPostRepository();
        regionRepository = new DBRegionRepository();
        userRepository = new JsonUserRepository();
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public RegionRepository getRegionRepository() {
        return regionRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public static synchronized ObjectRepository getInstance() {
        if (instance == null) {
            instance = new ObjectRepository();
        }
        return instance;
    }
}

package ru.yauroff.test.mainproject.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.yauroff.test.mainproject.repository.PostRepository;
import ru.yauroff.test.mainproject.repository.RegionRepository;
import ru.yauroff.test.mainproject.repository.UserRepository;

public class ObjectRepository {
    private static final Logger logger = LogManager.getLogger(ObjectRepository.class);
    private static ObjectRepository instance;
    private static SessionFactory sessionFactory;
    private PostRepository postRepository;
    private RegionRepository regionRepository;
    private UserRepository userRepository;

    private ObjectRepository() {
        createSessionFactory();
        postRepository = new HibPostRepository();
        regionRepository = new HibRegionRepository();
        userRepository = new HibUserRepository();
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static synchronized ObjectRepository getInstance() {
        if (instance == null) {
            instance = new ObjectRepository();
        }
        return instance;
    }

    private void createSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            logger.error("Ошибка при создании SessionFactory!", e);
            System.exit(0);
        }
    }
}

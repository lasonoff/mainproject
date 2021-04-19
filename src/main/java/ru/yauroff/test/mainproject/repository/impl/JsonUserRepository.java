package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;

/**
 * Created by ayaurov on 19.04.2021.
 */
public class JsonUserRepository extends JsonRepository<User, Long> implements UserRepository {

    public JsonUserRepository(String filePath) {
        super(filePath, User.class);
    }

    @Override
    protected Long getId(User entity) {
        return entity.getId();
    }

    @Override
    protected void updateEntity(User entityForUpdate, User entity) {
        entityForUpdate.setFirstName(entity.getFirstName());
        entityForUpdate.setLastName(entity.getLastName());
        entityForUpdate.setPosts(entity.getPosts());
        entityForUpdate.setRegion(entity.getRegion());
        entityForUpdate.setRole(entity.getRole());
    }
}

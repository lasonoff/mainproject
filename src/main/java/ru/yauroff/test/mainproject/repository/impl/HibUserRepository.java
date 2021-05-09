package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;

public class HibUserRepository extends HibRepository<User, Long> implements UserRepository {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected Long getEntityId(User entity) {
        return entity.getId();
    }

    @Override
    protected void updateEntity(User entityFromDB, User entity) {
        entityFromDB.setFirstName(entity.getFirstName());
        entityFromDB.setLastName(entity.getLastName());
        entityFromDB.setRegionId(entity.getRegionId());
        entityFromDB.setRole(entity.getRole());
    }
}

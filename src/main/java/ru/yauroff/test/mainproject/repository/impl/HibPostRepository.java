package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.repository.PostRepository;

public class HibPostRepository extends HibRepository<Post, Long> implements PostRepository {
    @Override
    protected Class<Post> getEntityClass() {
        return Post.class;
    }

    @Override
    protected Long getEntityId(Post entity) {
        return entity.getId();
    }

    @Override
    protected void updateEntity(Post entityFromDB, Post entity) {
        entityFromDB.setContent(entity.getContent());
    }
}

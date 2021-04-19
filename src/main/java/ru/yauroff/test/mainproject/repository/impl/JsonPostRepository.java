package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.repository.PostRepository;

/**
 * Created by ayaurov on 15.04.2021.
 */
public class JsonPostRepository extends JsonRepository<Post, Long> implements PostRepository {

    public JsonPostRepository(String filePath) {
        super(filePath, Post.class);
    }

    @Override
    protected Long getId(Post obj) {
        return obj.getId();
    }

    @Override
    protected void updateEntity(Post entityForUpdate, Post entity) {
        entityForUpdate.setUpdated(entity.getUpdated());
        entityForUpdate.setContent(entity.getContent());
    }
}

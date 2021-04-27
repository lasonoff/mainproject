package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.repository.PostRepository;


public class JsonPostRepository extends JsonRepository<Post, Long> implements PostRepository {
    public static String pathToJson = System.getProperty("user.dir") + System.getProperty("file" + ".separator") + "post" +
            ".json";

    public JsonPostRepository() {
        super(pathToJson, Post.class);
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

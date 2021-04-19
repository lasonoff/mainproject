package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.repository.PostRepository;

import java.util.List;

/**
 * Created by ayaurov on 15.04.2021.
 */
public class JsonPostRepository extends JsonRepository<Post, Long> implements PostRepository {

    public JsonPostRepository(String filePath) {
        super(filePath, Post.class);
    }

    @Override
    public Post update(Post entity) {
        List<Post> objects = jsonDM.readAll();
        Post obj = objects.stream().filter(x -> entity.equals(x)).findFirst().orElse(null);
        if (obj != null) {
            obj.setUpdated(entity.getUpdated());
            obj.setContent(entity.getContent());
            jsonDM.writeAll(objects);
            return obj;
        }
        return null;
    }

    @Override
    public List<Post> updateAll(List<Post> entities) {
        return null;
    }

    @Override
    protected Long getId(Post obj) {
        return obj.getId();
    }
}

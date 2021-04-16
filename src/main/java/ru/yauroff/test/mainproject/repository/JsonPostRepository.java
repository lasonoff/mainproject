package ru.yauroff.test.mainproject.repository;

import ru.yauroff.test.mainproject.model.Post;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ayaurov on 15.04.2021.
 */
public class JsonPostRepository extends JsonRepository<Post> implements PostRepository {

    public JsonPostRepository(String filePath) {
        super(filePath);
        setType(Post.class);
    }


    @Override
    public void create(Post entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().getMostSignificantBits());
        }
        if (entity.getCreated() == null) {
            entity.setCreated(new Date());
        }
        entity.setUpdated(new Date());
        write(entity);
    }

    @Override
    public void delete(Post entity) {
        List<Post> objects = readAll();
        List<Post> objectsDeleted = objects.stream().filter(x -> !x.equals(entity)).collect(Collectors.toList());
        if (objectsDeleted.size() != objects.size()) {
            writeAll(objectsDeleted);
        }
    }

    @Override
    public void deleteAll() {
        writeAll(new ArrayList<>());
    }

    @Override
    public void deleteById(Long id) {
        List<Post> objects = readAll();
        List<Post> objectsDeleted = objects.stream().filter(x -> !id.equals(x.getId())).collect(Collectors.toList());
        if (objectsDeleted.size() != objects.size()) {
            writeAll(objectsDeleted);
        }
    }

    @Override
    public List<Post> findAll() {
        List<Post> res = this.readAll();
        return res;
    }

    @Override
    public List<Post> findAllById(List<Long> ids) {
        List<Post> objects = readAll();
        Set<Long> setIds = new HashSet<>(ids);
        List<Post> findObjects = objects.stream().filter(x -> setIds.contains(x.getId())).collect(Collectors.toList());
        return findObjects;
    }

    @Override
    public Optional<Post> findById(Long id) {
        List<Post> objects = readAll();
        return objects.stream().filter(x -> id.equals(x.getId())).findFirst();
    }

    @Override
    public Post update(Post entity) {
        List<Post> objects = readAll();
        Post obj = objects.stream().filter(x -> entity.equals(x)).findFirst().orElse(null);
        if (obj != null) {
            obj.setUpdated(new Date());
            obj.setContent(entity.getContent());
            writeAll(objects);
            return obj;
        }
        return null;
    }

    @Override
    public List<Post> updateAll(List<Post> entities) {
        return null;
    }
}

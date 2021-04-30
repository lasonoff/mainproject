package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Post;
import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.model.User;
import ru.yauroff.test.mainproject.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonUserRepository extends JsonRepository<User, Long> implements UserRepository {
    static String pathToJson = System.getProperty("user.dir") + System.getProperty("file" + ".separator") + "user" +
            ".json";

    private void injectObjects(User user) {
        if (user != null) {
            /*List<Post> posts = ObjectRepository.getInstance().getPostRepository().findAllById(user.getPostsId());
            user.setPosts(posts);*/
            Region region = ObjectRepository.getInstance().getRegionRepository().findById(user.getRegionId()).orElse(null);
            user.setRegion(region);
        }
    }

    public JsonUserRepository() {
        super(pathToJson, User.class);
    }

    @Override
    public List<User> findAll() {
        return super.findAll().stream().peek(x -> injectObjects(x)).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllById(List<Long> ids) {
        return super.findAllById(ids).stream().peek(x -> injectObjects(x)).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> res = super.findById(id);
        injectObjects(res.orElse(null));
        return res;
    }

    @Override
    protected Long getId(User entity) {
        return entity.getId();
    }

    @Override
    protected void updateEntity(User entityForUpdate, User entity) {
        entityForUpdate.setFirstName(entity.getFirstName());
        entityForUpdate.setLastName(entity.getLastName());
        //entityForUpdate.setPostsId(entity.getPostsId());
        entityForUpdate.setRegionId(entity.getRegionId());
        entityForUpdate.setRole(entity.getRole());
    }
}

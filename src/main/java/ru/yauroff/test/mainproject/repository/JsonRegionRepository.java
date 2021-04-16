package ru.yauroff.test.mainproject.repository;

import ru.yauroff.test.mainproject.model.Region;

import java.util.List;
import java.util.Optional;

/**
 * Created by ayaurov on 16.04.2021.
 */
public class JsonRegionRepository extends JsonRepository<Region> implements RegionRepository {
    public JsonRegionRepository(String filePath) {
        super(filePath);
        setType(Region.class);
    }

    @Override
    public void create(Region entity) {
        write(entity);
    }

    @Override
    public void delete(Region entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<Region> findAll() {
        return null;
    }

    @Override
    public List<Region> findAllById(List<Long> longs) {
        return null;
    }

    @Override
    public Optional<Region> findById(Long aLong) {
        return null;
    }

    @Override
    public Region update(Region entity) {
        return null;
    }

    @Override
    public List<Region> updateAll(List<Region> entities) {
        return null;
    }
}

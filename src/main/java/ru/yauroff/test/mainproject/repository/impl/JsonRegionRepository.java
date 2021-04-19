package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.repository.RegionRepository;

import java.util.List;


public class JsonRegionRepository extends JsonRepository<Region, Long> implements RegionRepository {

    public JsonRegionRepository(String filePath) {
        super(filePath, Region.class);
    }

    @Override
    public Region update(Region entity) {
        return null;
    }

    @Override
    public List<Region> updateAll(List<Region> entities) {
        return null;
    }

    @Override
    protected Long getId(Region obj) {
        return obj.getId();
    }
}

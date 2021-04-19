package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.repository.RegionRepository;


public class JsonRegionRepository extends JsonRepository<Region, Long> implements RegionRepository {

    public JsonRegionRepository(String filePath) {
        super(filePath, Region.class);
    }

    @Override
    protected Long getId(Region obj) {
        return obj.getId();
    }

    @Override
    protected void updateEntity(Region entityForUpdate, Region entity) {
        entityForUpdate.setName(entity.getName());
    }


}

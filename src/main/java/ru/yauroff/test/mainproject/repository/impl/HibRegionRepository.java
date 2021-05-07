package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.repository.RegionRepository;

public class HibRegionRepository extends HibRepository<Region, Long> implements RegionRepository {
    @Override
    protected Class<Region> getEntityClass() {
        return Region.class;
    }

    @Override
    protected Long getEntityId(Region entity) {
        return entity.getId();
    }

    @Override
    protected void updateEntity(Region entityFromDB, Region entity) {
        entityFromDB.setName(entity.getName());
    }
}

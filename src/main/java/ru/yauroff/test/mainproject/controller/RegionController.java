package ru.yauroff.test.mainproject.controller;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.repository.RegionRepository;
import ru.yauroff.test.mainproject.repository.impl.ObjectRepository;

import java.util.Date;


public class RegionController {
    RegionRepository repository = ObjectRepository.getInstance().getRegionRepository();

    public void create(String name) {
        Region region = new Region(new Date().getTime(), name);
        repository.create(region);
    }

    public void update(Region region, String name) {
        region.setName(name);
        repository.update(region);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

package ru.yauroff.test.mainproject.controller;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.repository.RegionRepository;

import java.util.UUID;


public class RegionController {
    RegionRepository repository;

    public RegionController(RegionRepository repository) {
        this.repository = repository;
    }

    public void create(String name) {
        Region region = new Region(UUID.randomUUID().getMostSignificantBits(), name);
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

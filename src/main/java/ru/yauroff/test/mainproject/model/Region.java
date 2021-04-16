package ru.yauroff.test.mainproject.model;

/**
 * Created by ayaurov on 15.04.2021.
 */
public class Region {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Region{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

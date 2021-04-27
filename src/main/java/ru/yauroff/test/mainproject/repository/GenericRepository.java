package ru.yauroff.test.mainproject.repository;

import java.util.List;
import java.util.Optional;


public interface GenericRepository<T, ID> {

    public long count();

    public void create(T entity);

    public void delete(T entity);

    public void deleteAll();

    public void deleteById(ID id);

    public List<T> findAll();

    public List<T> findAllById(List<ID> ids);

    public Optional<T> findById(ID id);

    public T update(T entity);

    public List<T> updateAll(List<T> entities);
}

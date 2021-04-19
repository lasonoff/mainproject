package ru.yauroff.test.mainproject.repository.impl;

import java.util.*;
import java.util.stream.Collectors;

abstract class JsonRepository<T, ID> {
    protected JsonFileDataManager<T> jsonDM;

    public JsonRepository(String filePath, Class<T> type) {
        jsonDM = new JsonFileDataManager(filePath, type);
    }

    public long count() {
        return jsonDM.readAll().size();
    }

    public void create(T entity) {
        jsonDM.write(entity);
    }

    public void delete(T entity) {
        List<T> objects = jsonDM.readAll();
        List<T> objectsDeleted = objects.stream().filter(x -> !x.equals(entity)).collect(Collectors.toList());
        if (objectsDeleted.size() != objects.size()) {
            jsonDM.writeAll(objectsDeleted);
        }
    }

    public void deleteAll() {
        jsonDM.writeAll(new ArrayList<>());
    }

    public void deleteById(ID id) {
        List<T> objects = jsonDM.readAll();
        List<T> objectsDeleted = objects.stream().filter(x -> !id.equals(getId(x))).collect(Collectors.toList());
        if (objectsDeleted.size() != objects.size()) {
            jsonDM.writeAll(objectsDeleted);
        }
    }

    public List<T> findAll() {
        List<T> res = jsonDM.readAll();
        return res;
    }

    public List<T> findAllById(List<ID> ids) {
        List<T> objects = jsonDM.readAll();
        Set<ID> setIds = new HashSet<>(ids);
        List<T> findObjects = objects.stream().filter(x -> setIds.contains(getId(x))).collect(Collectors.toList());
        return findObjects;
    }

    public Optional<T> findById(ID id) {
        List<T> objects = jsonDM.readAll();
        return objects.stream().filter(x -> id.equals(getId(x))).findFirst();
    }

    public T update(T entity) {
        List<T> objects = jsonDM.readAll();
        T obj = objects.stream().filter(x -> getId(entity).equals(getId(x))).findFirst().orElse(null);
        if (obj != null) {
            updateEntity(obj, entity);
            jsonDM.writeAll(objects);
            return obj;
        }
        return null;
    }

    public List<T> updateAll(List<T> entities) {
        return entities.stream().map(x -> update(x)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Возваращает первичный ключ переданного объекта.
     *
     * @param entity
     * @return ID - первичный ключ объекта.
     */
    abstract protected ID getId(T entity);

    /**
     * Обновляет состояние объекта используя другой.
     *
     * @param entityForUpdate - объект, который необходимо обновить;
     * @param entity          - объект, состояние которого используем для обновления.
     */
    abstract protected void updateEntity(T entityForUpdate, T entity);

}

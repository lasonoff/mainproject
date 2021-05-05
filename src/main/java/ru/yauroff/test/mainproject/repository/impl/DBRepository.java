package ru.yauroff.test.mainproject.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.yauroff.test.mainproject.repository.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


abstract public class DBRepository<T, ID> {
    private static final Logger logger = LogManager.getLogger(DBRepository.class);

    private static String countSql = "SELECT COUNT(*) AS count FROM %s;";
    private static String findAllSql = "SELECT * FROM %s";
    private static String deleteAllSql = "DELETE FROM %s;";

    public long count() {
        String sql = getCountSql();
        long res = 0;
        try (PreparedStatement statement = ConnectionFactory.getInstance().getConnection()
                .prepareStatement(sql)) {

            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                res = rs.getLong("count");
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении кол- ва данных из " + getMainTableName(), e);
        }
        return res;
    }

    public void create(T entity) {
        String sql = getCreateSql();
        try (PreparedStatement statement = ConnectionFactory.getInstance().getConnection()
                .prepareStatement(sql)) {
            updateStatement(statement, 1, entity);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Ошибка при создание нового объекта в таблице " + getMainTableName(), e);
        }
    }

    public List<T> findAll() {
        String sql = getFindAllSql();
        List<T> res = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getInstance().getConnection()
                .prepareStatement(sql)) {

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    T entity = getNewEntity(rs);
                    res.add(entity);
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении всех данных из " + getMainTableName(), e);
        }
        return res;
    }

    public List<T> findAllById(List<ID> ids) {
        List<T> findObjects = ids.stream().map(id -> this.findById(id).get()).filter(Objects::nonNull).collect
                (Collectors.toList());
        return findObjects;
    }

    public Optional<T> findById(ID id) {
        String sql = getFindByIdSql();
        try (PreparedStatement statement = ConnectionFactory.getInstance().getConnection()
                .prepareStatement(sql)) {

            setIdIntoStatement(statement, 1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    T entity = getNewEntity(rs);
                    return Optional.of(entity);
                }
            }
        } catch (SQLException e) {
            logger.error("Ошибка при получении объекта по id из " + getMainTableName(), e);
        }
        return Optional.of(null);
    }

    public T update(T entity) {
        String sql = getUpdateSql();
        try (PreparedStatement statement = ConnectionFactory.getInstance().getConnection()
                .prepareStatement(sql)) {
            int endUpdNum = updateStatement(statement, 1, entity);
            setIdIntoStatement(statement, endUpdNum + 1, getIdEntity(entity));
            statement.execute();
        } catch (SQLException e) {
            logger.error("Ошибка при обновлении объекта в таблице " + getMainTableName(), e);
        }
        return entity;
    }

    public List<T> updateAll(List<T> entities) {
        return entities.stream().map(entity -> update(entity)).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void deleteAll() {
        String sql = getDeleteAllSql();
        try (Statement statement = ConnectionFactory.getInstance().getConnection()
                .createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            logger.error("Ошибка при удалении всех данных из " + getMainTableName(), e);
        }
    }

    public void delete(T entity) {
        deleteById(getIdEntity(entity));
    }

    public void deleteById(ID id) {
        String sql = getDeleteByIdSql();
        try (PreparedStatement statement = ConnectionFactory.getInstance().getConnection()
                .prepareStatement(sql)) {
            setIdIntoStatement(statement, 1, id);
            statement.execute();
        } catch (SQLException e) {
            logger.error("Ошибка при удалении объекта по id из таблицы " + getMainTableName(), e);
        }
    }

    protected String getCountSql() {
        return String.format(countSql, getMainTableName());
    }

    protected String getFindAllSql() {
        return String.format(findAllSql, getMainTableName());
    }

    protected String getDeleteAllSql() {
        return String.format(deleteAllSql, getMainTableName());
    }

    protected abstract String getCreateSql();

    protected abstract String getFindByIdSql();

    protected abstract String getUpdateSql();

    protected abstract String getDeleteByIdSql();

    protected abstract int updateStatement(PreparedStatement statement, int startWith, T entity) throws SQLException;

    protected abstract void setIdIntoStatement(PreparedStatement statement, int paramNum, ID id) throws SQLException;

    protected abstract String getMainTableName();

    protected abstract T getNewEntity(ResultSet rs) throws SQLException;

    protected abstract ID getIdEntity(T entity);

}

package ru.yauroff.test.mainproject.repository.impl;

import ru.yauroff.test.mainproject.model.Region;
import ru.yauroff.test.mainproject.repository.RegionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBRegionRepository extends DBRepository<Region, Long> implements RegionRepository {

    @Override
    protected Region getNewEntity(ResultSet rs) throws SQLException {
        return new Region(rs.getLong("id"), rs.getString("name"));
    }

    @Override
    protected Long getIdEntity(Region entity) {
        return entity.getId();
    }

    @Override
    protected String getMainTableName() {
        return "region";
    }

    @Override
    protected String getCreateSql() {
        return "INSERT INTO region(name) VALUES (?)";
    }

    @Override
    protected String getFindByIdSql() {
        return "SELECT * FROM region WHERE ID = ?";
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE region SET name = ? WHERE ID = ?";
    }

    @Override
    protected String getDeleteByIdSql() {
        return "DELETE FROM region WHERE ID = ?";
    }

    @Override
    protected int updateStatement(PreparedStatement statement, int startWith, Region entity) throws SQLException {
        statement.setString(startWith + 1, entity.getName());
        return startWith + 1;
    }

    @Override
    protected void setIdIntoStatement(PreparedStatement statement, int paramNum, Long id) throws SQLException {
        statement.setLong(paramNum, id);
    }
}
